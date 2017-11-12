package com.xsmile2008.khaki.view_model

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import com.xsmile2008.khaki.AppClass
import com.xsmile2008.khaki.db.AppDatabase
import com.xsmile2008.khaki.entities.Human
import kotlinx.coroutines.experimental.async
import javax.inject.Inject

/**
 * Created by vladstarikov on 10/21/17.
 */
class HumansViewModel : ViewModel() {

    @Inject
    lateinit var db: AppDatabase

    private val humans: LiveData<List<Human>>

    init {
        AppClass.component.inject(this)
        humans = db.humanDao().getAll()
    }

    fun getHumans(): LiveData<List<Human>> = humans

    fun deleteHuman(human: Human) {
        async {
            db.humanDao().delete(human)
        }
    }
}