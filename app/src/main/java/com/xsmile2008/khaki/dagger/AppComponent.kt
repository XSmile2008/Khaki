package com.xsmile2008.khaki.dagger

import com.xsmile2008.khaki.activities.HumanDetailsActivity
import com.xsmile2008.khaki.fragments.HumansFragment
import dagger.Component

/**
 * Created by vladstarikov on 10/21/17.
 */

@Component(modules = arrayOf(AppModule::class))
interface AppComponent {

    fun inject(activity: HumanDetailsActivity)
    fun inject(fragment: HumansFragment)
}
