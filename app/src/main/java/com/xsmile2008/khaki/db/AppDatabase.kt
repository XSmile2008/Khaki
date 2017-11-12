package com.xsmile2008.khaki.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverter
import android.arch.persistence.room.TypeConverters
import com.xsmile2008.khaki.dao.HumanDao
import com.xsmile2008.khaki.dao.MilitaryCardDao
import com.xsmile2008.khaki.dao.PassportDao
import com.xsmile2008.khaki.db.converter.DateConverter
import com.xsmile2008.khaki.db.converter.GenderConverter
import com.xsmile2008.khaki.entities.Human
import com.xsmile2008.khaki.entities.MilitaryCard
import com.xsmile2008.khaki.entities.Passport

/**
 * Created by vladstarikov on 10/21/17.
 */
@Database(
        version = 1,
        entities = arrayOf(
                Human::class,
                Passport::class,
                MilitaryCard::class
        )
)
@TypeConverters(
        DateConverter::class,
        GenderConverter::class
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun humanDao(): HumanDao

    abstract fun passportDao(): PassportDao

    abstract fun militaryCardDao(): MilitaryCardDao
}