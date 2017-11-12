package com.xsmile2008.khaki.dao

import android.arch.persistence.room.*
import com.xsmile2008.khaki.entities.Human
import com.xsmile2008.khaki.entities.Passport

/**
 * Created by vladstarikov on 10/21/17.
 */

@Dao
interface PassportDao {

    @Query("SELECT * FROM passports")
    fun getAll(): List<Passport>

    @Query("SELECT * FROM passports WHERE number = :number")
    fun findByNumber(number: String): Passport?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(passport: Passport)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(passports: Array<Passport>)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun update(passport: Passport)

    @Delete
    fun delete(passport: Passport)

    @Query("DELETE FROM passports")
    fun deleteAll(): Int
}