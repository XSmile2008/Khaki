package com.xsmile2008.khaki.activities

import android.app.Activity
import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.DatePicker
import android.widget.Toast
import com.xsmile2008.khaki.AppClass
import com.xsmile2008.khaki.R
import com.xsmile2008.khaki.consts.HUMAN_ID
import com.xsmile2008.khaki.consts.MILITARY_CARD
import com.xsmile2008.khaki.consts.NOT_FOUND_LONG
import com.xsmile2008.khaki.db.AppDatabase
import com.xsmile2008.khaki.entities.MilitaryCard
import com.xsmile2008.khaki.utils.formatDate
import kotlinx.android.synthetic.main.activity_military_card_details.*
import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.async
import java.util.*
import javax.inject.Inject

/**
 * Created by vladstarikov on 11/12/17.
 */
class MilitaryCardDetailsActivity : BaseActivity(), DatePickerDialog.OnDateSetListener {

    companion object {
        val REQUEST_CODE = 118
    }

    @Inject
    lateinit var db: AppDatabase

    private var militaryCard: MilitaryCard? = null
    private var date: Date? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppClass.component.inject(this)
        setContentView(R.layout.activity_military_card_details)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)

        val humanId = intent.getLongExtra(HUMAN_ID, NOT_FOUND_LONG)

        async(CommonPool) {
            militaryCard = db.militaryCardDao().findByHumanId(humanId)
            runOnUiThread {
                updateUi(militaryCard)
            }
        }

        btn_select_creation_date.setOnClickListener {
            showDatePickerDialog(militaryCard?.creationDate)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_save, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean = when (item!!.itemId) {
        R.id.action_save -> {
            if (f_military_card_number.text.isEmpty()
                    || f_authority.text.isEmpty()
                    || (militaryCard == null && date == null)) {
                Toast.makeText(this, "Not all fields filled!", Toast.LENGTH_SHORT).show()
            } else {
                async {
                    try {
                        if (militaryCard != null) {
                            militaryCard?.let {
                                date?.let { date -> it.creationDate = date }
                                it.authority = f_authority.text.toString()
                                db.militaryCardDao().update(it)
                            }
                            setResult(Activity.RESULT_OK)
                        } else {
                            val humanId = intent.getLongExtra(HUMAN_ID, NOT_FOUND_LONG)
                            val militaryCard = MilitaryCard(
                                    f_military_card_number.text.toString(),
                                    humanId,
                                    f_authority.text.toString(),
                                    date!!//TODO:
                            )
                            if (humanId != NOT_FOUND_LONG) {
                                db.militaryCardDao().insert(militaryCard)
                                setResult(Activity.RESULT_OK)
                            } else {
                                setResult(Activity.RESULT_OK, Intent().putExtra(MILITARY_CARD, militaryCard))
                            }
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

    private fun updateUi(militaryCard: MilitaryCard?) {
        f_military_card_number.setText(militaryCard?.number)
        f_authority.setText(militaryCard?.authority)

        val creationDate = militaryCard?.creationDate
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