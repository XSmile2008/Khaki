package com.xsmile2008.khaki.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.xsmile2008.khaki.dao.HumanDao
import com.xsmile2008.khaki.dao.PassportDao
import com.xsmile2008.khaki.entities.Human
import com.xsmile2008.khaki.entities.Passport

/**
 * Created by vladstarikov on 10/21/17.
 */
@Database(entities = arrayOf(Human::class, Passport::class), version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun humanDao(): HumanDao

    abstract fun passportsDao(): PassportDao
}