package com.xsmile2008.khaki.entities

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.ForeignKey
import android.arch.persistence.room.PrimaryKey
import java.util.*

/**
 * Created by vladstarikov on 10/21/17.
 */
@Entity(
        tableName = "military_cards",
        foreignKeys = arrayOf(
                ForeignKey(
                        entity = Human::class,
                        parentColumns = arrayOf("id"),
                        childColumns = arrayOf("human_id"),
                        onDelete = ForeignKey.CASCADE
                )
        )
)
class MilitaryCard(
        @PrimaryKey
        @ColumnInfo(name = "number") var number: String,
        @ColumnInfo(name = "human_id") var humanId: Long,
        @ColumnInfo(name = "authority") var authority: String,
        @ColumnInfo(name = "creation_date") var creationDate: Date
)