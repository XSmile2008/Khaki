package com.xsmile2008.khaki.entities

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import java.util.*

/**
 * Created by vladstarikov on 10/21/17.
 */
@Entity(tableName = "passports")
class Passport(
        @PrimaryKey
        @ColumnInfo(name = "number") var number: String,
        @ColumnInfo(name = "authority") var authority: String,
        @ColumnInfo(name = "creation_date") var creationDate: Date
)