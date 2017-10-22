package com.xsmile2008.khaki.activities

import android.app.Application
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.xsmile2008.khaki.AppClass
import com.xsmile2008.khaki.R
import com.xsmile2008.khaki.navigators.MainNavigator
import ru.terrakok.cicerone.Navigator
import ru.terrakok.cicerone.Router
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var app: Application

    val navigator: Navigator = MainNavigator(supportFragmentManager, R.id.container)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        AppClass.component.inject(this)
    }
}