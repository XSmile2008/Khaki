package com.xsmile2008.khaki.activities

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.xsmile2008.khaki.AppClass
import com.xsmile2008.khaki.R
import com.xsmile2008.khaki.db.AppDatabase
import com.xsmile2008.khaki.model.HumanDetailsViewModel
import javax.inject.Inject

/**
 * Created by vladstarikov on 10/21/17.
 */

class HumanDetailsActivity : AppCompatActivity() {

    @Inject
    lateinit var db: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (application as AppClass).component.inject(this)
        setContentView(R.layout.activity_human_details)

        val model = ViewModelProviders.of(this).get(HumanDetailsViewModel::class.java)
    }
}
