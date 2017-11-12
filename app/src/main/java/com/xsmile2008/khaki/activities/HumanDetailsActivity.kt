package com.xsmile2008.khaki.activities

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils.isEmpty
import android.view.Menu
import android.view.MenuItem
import android.widget.DatePicker
import com.xsmile2008.khaki.AppClass
import com.xsmile2008.khaki.R
import com.xsmile2008.khaki.consts.HUMAN_ID
import com.xsmile2008.khaki.entities.Human
import com.xsmile2008.khaki.entities.Passport
import com.xsmile2008.khaki.enums.Gender
import com.xsmile2008.khaki.utils.formatDate
import com.xsmile2008.khaki.view_model.HumanDetailsViewModel
import kotlinx.android.synthetic.main.activity_human_details.*
import java.util.*

/**
 * Created by vladstarikov on 10/21/17.
 */

class HumanDetailsActivity : BaseActivity(), DatePickerDialog.OnDateSetListener {

    private lateinit var model: HumanDetailsViewModel

    private var gender: Gender? = null
    private var birthday: Date? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppClass.component.inject(this)
        setContentView(R.layout.activity_human_details)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)

        val humanId = intent.getLongExtra(HUMAN_ID, -1)

        model = ViewModelProviders.of(this).get(HumanDetailsViewModel::class.java)
        model.getHuman().observe(this, Observer<Human> { updateUi(it) })
        model.getPassport().observe(this, Observer<Passport> { setPassportText(it?.number) })
        model.fetchHuman(humanId)
        model.fetchPassport(humanId)

        btn_select_birthday.setOnClickListener {
            showBirthdayDialog(model.getHuman().value?.birthday)
        }

        btn_select_gender.setOnClickListener {
            showGenderSelectDialog(model.getHuman().value?.gender)
        }

        btn_create_edit_passport.setOnClickListener {
            showPassportDetails(humanId)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_save, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean = when (item!!.itemId) {
        R.id.action_save -> {
            true
        }
        else -> super.onOptionsItemSelected(item)
    }

    private fun updateUi(human: Human?) {
        f_first_name.setText(human?.firstName)
        f_middle_name.setText(human?.middleName)
        f_last_name.setText(human?.lastName)
        setBirthdayText(human?.birthday)
        setGenderText(human?.gender)
    }

//BirthDay
//--------------------------------------------------------------------------------------------------

    private fun setBirthdayText(date: Date?) = if (date == null) {
        txt_birthday.setText(R.string.empty)
    } else {
        txt_birthday.text = formatDate(date)
    }

    private fun showBirthdayDialog(date: Date?) {
        val calendar = Calendar.getInstance()
        date?.let { calendar.time = it }
        DatePickerDialog(
                this,
                this,
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
        ).show()
    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, day: Int) {
        val calendar = Calendar.getInstance()
        calendar.clear()
        calendar.set(Calendar.YEAR, year)
        calendar.set(Calendar.MONTH, month)
        calendar.set(Calendar.DAY_OF_MONTH, day)
        calendar.time.let {
            birthday = it
            setBirthdayText(it)
        }
    }

//Gender
//--------------------------------------------------------------------------------------------------

    private fun setGenderText(gender: Gender?) = if (gender == null) {
        txt_gender.setText(R.string.empty)
    } else {
        txt_gender.text = gender.value.capitalize()
    }

    private fun showGenderSelectDialog(oldGender: Gender?) {
        val values = Gender.values()
        val items = values.map { it.value.capitalize() }.toTypedArray()
        var selected: Gender? = oldGender
        AlertDialog.Builder(this)
                .setTitle("Select gender")
                .setSingleChoiceItems(
                        items,
                        values.indexOf(selected),
                        { _, i -> selected = values[i] }
                )
                .setNegativeButton("Cancel", null)
                .setPositiveButton("Done", { _, _ ->
                    selected?.let {
                        gender = it
                        setGenderText(it)
                    }
                })
                .create()
                .show()
    }

//Passport
//--------------------------------------------------------------------------------------------------

    private fun setPassportText(passportNumber: String?) = if (isEmpty(passportNumber)) {
        txt_passport.setText(R.string.empty)
    } else {
        txt_passport.text = passportNumber
    }

    private fun showPassportDetails(humanId: Long) {
        startActivityForResult(
                Intent(this, PassportDetailsActivity::class.java).putExtra(HUMAN_ID, humanId),
                0
        )
    }
}