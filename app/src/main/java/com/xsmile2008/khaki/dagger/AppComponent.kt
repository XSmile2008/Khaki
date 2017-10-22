package com.xsmile2008.khaki.dagger

import com.xsmile2008.khaki.activities.HumanDetailsActivity
import com.xsmile2008.khaki.activities.MainActivity
import com.xsmile2008.khaki.fragments.HumansFragment
import dagger.Component

/**
 * Created by vladstarikov on 10/21/17.
 */

@Component(modules = arrayOf(AppModule::class))
interface AppComponent {

    //Activities
    fun inject(mainActivity: MainActivity)
    fun inject(activity: HumanDetailsActivity)

    //Fragments
    fun inject(fragment: HumansFragment)

    //Other
}
