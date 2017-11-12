package com.xsmile2008.khaki.dao

import android.arch.persistence.room.*
import com.xsmile2008.khaki.entities.MilitaryCard

/**
 * Created by vladstarikov on 10/21/17.
 */

@Dao
interface MilitaryCardDao {

    @Query("SELECT * FROM military_cards")
    fun getAll(): List<MilitaryCard>

    @Query("SELECT * FROM military_cards WHERE number = :number")
    fun findByNumber(number: String): MilitaryCard?

    @Query("SELECT * FROM military_cards WHERE human_id = :humanId")
    fun findByHumanId(humanId: Long): MilitaryCard?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(passport: MilitaryCard)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(military_cards: Array<MilitaryCard>)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun update(passport: MilitaryCard)

    @Delete
    fun delete(passport: MilitaryCard)

    @Query("DELETE FROM military_cards")
    fun deleteAll(): Int
}