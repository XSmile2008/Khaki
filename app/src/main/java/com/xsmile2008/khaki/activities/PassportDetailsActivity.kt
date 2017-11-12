package com.xsmile2008.khaki.activities

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.DatePicker
import android.widget.Toast
import com.xsmile2008.khaki.AppClass
import com.xsmile2008.khaki.R
import com.xsmile2008.khaki.consts.HUMAN_ID
import com.xsmile2008.khaki.db.AppDatabase
import com.xsmile2008.khaki.entities.Passport
import com.xsmile2008.khaki.utils.formatDate
import kotlinx.android.synthetic.main.activity_passport.*
import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.async
import java.util.*
import javax.inject.Inject

/**
 * Created by vladstarikov on 11/12/17.
 */
class PassportDetailsActivity : BaseActivity(), DatePickerDialog.OnDateSetListener {

    companion object {
        val REQUEST_CODE = 117
    }

    @Inject
    lateinit var db: AppDatabase

    private var passport: Passport? = null
    private var date: Date? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppClass.component.inject(this)
        setContentView(R.layout.activity_passport)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)

        val humanId = intent.getLongExtra(HUMAN_ID, -1)

        async(CommonPool) {
            passport = db.passportDao().findByHumanId(humanId)
            runOnUiThread {
                updateUi(passport)
            }
        }

        btn_select_creation_date.setOnClickListener {
            showDatePickerDialog(passport?.creationDate)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_save, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean = when (item!!.itemId) {
        R.id.action_save -> {
            if (f_passport_number.text.isEmpty()
                    || f_authority.text.isEmpty()
                    || (passport == null && date == null)) {
                Toast.makeText(this, "Not all fields filled!", Toast.LENGTH_SHORT).show()
            } else {
                async {
                    try {
                        if (passport != null) {
                            passport?.let {
                                date?.let { date -> it.creationDate = date }
                                it.authority = f_authority.text.toString()
                                db.passportDao().update(it)
                            }
                        } else {
                            db.passportDao().insert(
                                    Passport(
                                            f_passport_number.text.toString(),
                                            intent.getLongExtra(HUMAN_ID, -1),
                                            f_authority.text.toString(),
                                            date!!//TODO:
                                    )
                            )
                        }
                        finish()
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
            }
            true
        }
        else -> super.onOptionsItemSelected(item)
    }

    private fun updateUi(passport: Passport?) {
        f_passport_number.setText(passport?.number)
        f_authority.setText(passport?.authority)

        val creationDate = passport?.creationDate
        txt_creation_date.text = if (creationDate != null) formatDate(creationDate) else getString(R.string.empty)
    }

    private fun showDatePickerDialog(date: Date?) {
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
            date = it
            txt_creation_date.text = formatDate(it)
        }
    }
}