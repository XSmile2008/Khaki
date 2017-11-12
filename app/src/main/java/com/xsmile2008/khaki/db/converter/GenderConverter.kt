package com.xsmile2008.khaki.db.converter

import android.arch.persistence.room.TypeConverter
import com.xsmile2008.khaki.enums.Gender
import java.util.*

/**
 * Created by vladstarikov on 11/12/17.
 */
class GenderConverter {

    @TypeConverter
    fun fromGender(value: Gender?): String? = value.toString()

    @TypeConverter
    fun fromDateToTimestamp(value: String?): Gender? = value?.let { Gender.valueOf(it) }
}