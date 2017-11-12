package com.xsmile2008.khaki.navigators

import android.app.Activity
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.widget.Toast
import com.xsmile2008.khaki.enums.Screen
import com.xsmile2008.khaki.fragments.HumansFragment
import ru.terrakok.cicerone.android.SupportFragmentNavigator

/**
 * Created by vladstarikov on 10/22/17.
 */
class MainNavigator(val activity: Activity, fragmentManager: FragmentManager, containerId: Int) : SupportFragmentNavigator(fragmentManager, containerId) {

    override fun createFragment(screenKey: String?, data: Any?): Fragment {
        when (screenKey) {
            Screen.HUMANS.name -> return HumansFragment()
            else -> TODO("not implemented")
        }
    }

    override fun exit() {
        activity.finish()
    }

    override fun showSystemMessage(message: String?) {
        Toast.makeText(activity, message, Toast.LENGTH_SHORT).show()
    }
}