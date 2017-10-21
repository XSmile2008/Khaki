package com.xsmile2008.khaki.model

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.xsmile2008.khaki.entities.Human

/**
 * Created by vladstarikov on 10/21/17.
 */
class HumansViewModel : ViewModel() {

    private val humans: LiveData<List<Human>>

    init {
        humans = MutableLiveData()//TODO
    }

    fun getHumans() = humans
}