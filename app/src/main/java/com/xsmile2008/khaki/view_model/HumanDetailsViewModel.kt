package com.xsmile2008.khaki.view_model

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.xsmile2008.khaki.AppClass
import com.xsmile2008.khaki.db.AppDatabase
import com.xsmile2008.khaki.entities.Human
import com.xsmile2008.khaki.entities.MilitaryCard
import com.xsmile2008.khaki.entities.Passport
import kotlinx.coroutines.experimental.async
import javax.inject.Inject

/**
 * Created by vladstarikov on 10/21/17.
 */
class HumanDetailsViewModel : ViewModel() {

    @Inject
    lateinit var db: AppDatabase

    private val human = MutableLiveData<Human>()
    private val passport = MutableLiveData<Passport>()
    private val militaryCard = MutableLiveData<MilitaryCard>()

    init {
        AppClass.component.inject(this)
    }

    fun getHuman(): LiveData<Human> = human

    fun getPassport(): LiveData<Passport> = passport

    fun getMilitaryCard(): LiveData<MilitaryCard> = militaryCard

    fun fetchHuman(humanId: Long) {
        async {
            human.postValue(db.humanDao().findById(humanId))
        }
    }

    fun fetchPassport(humanId: Long) {
        async {
            try {
                passport.postValue(db.passportDao().findByHumanId(humanId))
            } catch (e:Exception) {
                e.printStackTrace()
            }

        }
    }

    fun fetchMilitaryCard(humanId: Long) {
        async {
            try {
                militaryCard.postValue(db.militaryCardDao().findByHumanId(humanId))
            } catch (e:Exception) {
                e.printStackTrace()
            }

        }
    }

    fun save(human: Human) {
        async {
            db.humanDao().insert(human)
            this@HumanDetailsViewModel.human.value = human
        }
    }
}