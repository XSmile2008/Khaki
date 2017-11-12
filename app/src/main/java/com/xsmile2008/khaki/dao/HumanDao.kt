package com.xsmile2008.khaki.dao

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*
import android.arch.persistence.room.OnConflictStrategy.REPLACE
import com.xsmile2008.khaki.entities.Human

/**
 * Created by vladstarikov on 10/21/17.
 */

@Dao
interface HumanDao {

    @Query("SELECT * FROM humans")
    fun getAll(): LiveData<List<Human>>

//    @Query("SELECT * FROM humans")
//    fun getAll(): List<Human>

    @Query("SELECT * FROM humans WHERE id = :id")
    fun findById(id: Long): Human?

    @Insert(onConflict = REPLACE)
    fun insert(human: Human)

    @Insert(onConflict = REPLACE)
    fun insertAll(humans: Array<Human>)

    @Update(onConflict = REPLACE)
    fun update(human: Human)

    @Delete
    fun delete(human: Human)

    @Query("DELETE FROM humans")
    fun deleteAll(): Int
}
