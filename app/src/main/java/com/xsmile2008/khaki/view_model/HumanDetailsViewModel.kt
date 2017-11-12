package com.xsmile2008.khaki.view_model

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.util.Log
import com.xsmile2008.khaki.AppClass
import com.xsmile2008.khaki.db.AppDatabase
import com.xsmile2008.khaki.entities.Human
import kotlinx.coroutines.experimental.async
import javax.inject.Inject

/**
 * Created by vladstarikov on 10/21/17.
 */
class HumanDetailsViewModel : ViewModel() {

    @Inject
    lateinit var db: AppDatabase

    private val human = MutableLiveData<Human>()

    init {
        AppClass.component.inject(this)
    }

    fun getHuman(): LiveData<Human> = human

    fun fetch(id: Long) {
        async {
            human.postValue(db.humanDao().findById(id))
        }
    }

    fun save(human: Human) {
        async {
            db.humanDao().insert(human)
            this@HumanDetailsViewModel.human.value = db.humanDao().findById(human.id)
        }
    }
}