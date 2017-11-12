package com.xsmile2008.khaki.db.converter

import android.arch.persistence.room.TypeConverter
import java.util.*

/**
 * Created by vladstarikov on 11/12/17.
 */
class DateConverter {

    @TypeConverter
    fun fromTimestampToDate(value: Long?): Date? = value?.let { Date(it) }

    @TypeConverter
    fun fromDateToTimestamp(value: Date?): Long? = value?.time
}