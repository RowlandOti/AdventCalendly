package com.rowland.adventcalendly.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


/**
 * Created by Rowland on 1/25/2020.
 */


@Database(entities = [AdventDayEntity::class], version = 1, exportSchema = true)
abstract class AdventDatabase : RoomDatabase() {

    abstract fun adventDao(): AdventEntityDao

    companion object {
        private const val DB_NAME = "advent-calendly.db"

        @Volatile
        private var INSTANCE: AdventDatabase? = null

        @Synchronized
        fun getInstance(context: Context): AdventDatabase {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(
                    context,
                    AdventDatabase::class.java,
                    DB_NAME
                ).build()
            }
            return INSTANCE as AdventDatabase
        }
    }
}