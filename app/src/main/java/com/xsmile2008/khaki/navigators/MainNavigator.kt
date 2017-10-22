package com.xsmile2008.khaki.navigators

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import com.xsmile2008.khaki.entities.Screen
import ru.terrakok.cicerone.android.SupportFragmentNavigator

/**
 * Created by vladstarikov on 10/22/17.
 */
class MainNavigator(fragmentManager: FragmentManager, containerId: Int) : SupportFragmentNavigator(fragmentManager, containerId) {

    override fun createFragment(screenKey: String?, data: Any?): Fragment {
        when (screenKey) {
//            Screen.HUMANS ->
        }
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun exit() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showSystemMessage(message: String?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}