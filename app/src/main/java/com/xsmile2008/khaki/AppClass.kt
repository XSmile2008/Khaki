package com.xsmile2008.khaki

import android.app.Application
import com.xsmile2008.khaki.dagger.AppComponent
import com.xsmile2008.khaki.dagger.AppModule
import com.xsmile2008.khaki.dagger.DaggerAppComponent

/**
 * Created by vladstarikov on 10/21/17.
 */
class AppClass() : Application() {

    val component: AppComponent = DaggerAppComponent.builder().appModule(AppModule(this)).build()
}