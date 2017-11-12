package com.xsmile2008.khaki.activities

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.xsmile2008.khaki.AppClass
import com.xsmile2008.khaki.R
import com.xsmile2008.khaki.consts.HUMAN_ID
import com.xsmile2008.khaki.entities.Human
import com.xsmile2008.khaki.view_model.HumanDetailsViewModel
import kotlinx.android.synthetic.main.activity_human_details.*

/**
 * Created by vladstarikov on 10/21/17.
 */

class HumanDetailsActivity : AppCompatActivity() {

    private lateinit var model: HumanDetailsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppClass.component.inject(this)
        setContentView(R.layout.activity_human_details)

        model = ViewModelProviders.of(this).get(HumanDetailsViewModel::class.java)
        model.getHuman().observe(this, Observer<Human> { human -> human?.let { update(it) } })
        model.fetch(intent.getLongExtra(HUMAN_ID, -1))
    }

    private fun update(human: Human) {
        txt_id.text = human.id.toString()
        txt_first_name.text = human.firstName
        txt_middle_name.text = human.middleName
        txt_last_name.text = human.lastName
    }
}
