package com.xsmile2008.khaki.entities

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.ForeignKey
import android.arch.persistence.room.PrimaryKey
import com.xsmile2008.khaki.enums.Gender
import java.util.*

/**
 * Created by vladstarikov on 10/21/17.
 */
@Entity(
        tableName = "humans",
        foreignKeys = arrayOf(
                ForeignKey(
                        entity = Passport::class,
                        parentColumns = arrayOf("number"),
                        childColumns = arrayOf("passport_number"),
                        onDelete = ForeignKey.SET_NULL
                )
        )
)
class Human(
        @ColumnInfo(name = "first_name") var firstName: String,
        @ColumnInfo(name = "middle_name") var middleName: String,
        @ColumnInfo(name = "last_name") var lastName: String,
        @ColumnInfo(name = "birthday") var birthday: Date,
        @ColumnInfo(name = "gender") var gender: Gender,
        @ColumnInfo(name = "passport_number") var passportNumber: String?
) {

    @ColumnInfo(name = "id")
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
}