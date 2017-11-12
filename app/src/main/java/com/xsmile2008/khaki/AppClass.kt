package com.xsmile2008.khaki

import android.app.Application
import android.util.Log
import com.xsmile2008.khaki.dagger.AppComponent
import com.xsmile2008.khaki.dagger.AppModule
import com.xsmile2008.khaki.dagger.DaggerAppComponent
import com.xsmile2008.khaki.db.AppDatabase
import com.xsmile2008.khaki.entities.Human
import com.xsmile2008.khaki.entities.Passport
import com.xsmile2008.khaki.enums.Gender
import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.async
import java.util.*
import javax.inject.Inject

/**
 * Created by vladstarikov on 10/21/17.
 */
class AppClass : Application() {

    @Inject
    lateinit var db: AppDatabase

    init {
        component = DaggerAppComponent.builder().appModule(AppModule(this)).build()
        component.inject(this)
    }

    override fun onCreate() {
        super.onCreate()
        async(CommonPool) {
            try {
                db.humanDao().deleteAll()
                db.humanDao().insert(Human("Tako", "Burito", "Nachos", Date(), Gender.MALE))
                db.humanDao().insert(Human("Tako", "Burito", "Nachos", Date(), Gender.FEMALE))
                db.humanDao().insert(Human("Tako", "Burito", "Nachos", Date(), Gender.MALE))

                db.passportDao().deleteAll()
                db.passportDao().insert(Passport("HE627560", db.humanDao().getAll()[0].id, "tako", Date()))

                Log.d(AppClass::class.java.simpleName, "inited")
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    companion object {
        lateinit var component: AppComponent
    }
}