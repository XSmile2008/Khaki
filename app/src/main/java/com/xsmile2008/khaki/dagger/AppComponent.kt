package com.xsmile2008.khaki.dagger

import com.xsmile2008.khaki.AppClass
import com.xsmile2008.khaki.activities.HumanDetailsActivity
import com.xsmile2008.khaki.activities.MainActivity
import com.xsmile2008.khaki.activities.MilitaryCardDetailsActivity
import com.xsmile2008.khaki.activities.PassportDetailsActivity
import com.xsmile2008.khaki.fragments.HumansFragment
import com.xsmile2008.khaki.view_model.HumanDetailsViewModel
import com.xsmile2008.khaki.view_model.HumansViewModel
import dagger.Component
import javax.inject.Singleton

/**
 * Created by vladstarikov on 10/21/17.
 */

@Singleton
@Component(modules = arrayOf(AppModule::class))
interface AppComponent {
    //Application

    fun inject(appClass: AppClass)


    //Activities

    fun inject(activity: MainActivity)
    fun inject(activity: HumanDetailsActivity)
    fun inject(activity: PassportDetailsActivity)
    fun inject(activity: MilitaryCardDetailsActivity)


    //Fragments

    fun inject(fragment: HumansFragment)


    //Other

    fun inject(humansViewModel: HumansViewModel)
    fun inject(humanDetailsViewModel: HumanDetailsViewModel)
}
