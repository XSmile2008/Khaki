package com.xsmile2008.khaki.activities

import android.support.v7.app.AppCompatActivity
import android.view.MenuItem

/**
 * Created by vladstarikov on 11/12/17.
 */
abstract class BaseActivity : AppCompatActivity() {

    override fun onOptionsItemSelected(item: MenuItem?): Boolean = when (item?.itemId) {
        android.R.id.home -> {
            onBackPressed()
            true
        }
        else -> super.onOptionsItemSelected(item)
    }
}