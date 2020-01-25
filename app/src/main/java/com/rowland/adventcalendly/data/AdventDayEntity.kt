package com.rowland.adventcalendly.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

/**
 * Created by Rowland on 1/25/2020.
 */

@Entity(tableName = "advent_day" , indices = [Index(value = ["day"], unique = true)])
data class AdventDayEntity(@PrimaryKey(autoGenerate = true) val uid: Int =0,
                           @ColumnInfo(name = "day") var day: Int= 0,
                           @ColumnInfo(name = "month") val month: String,
                           @ColumnInfo(name = "is_openable") var isOpenable : Boolean= false,
                           @ColumnInfo(name = "is_redeemed") var isRedeemed : Boolean = false)