package com.rowland.adventcalendly.di.modules

import android.content.Context
import androidx.room.Room
import com.rowland.adventcalendly.cache.room.AdventDatabase
import com.rowland.adventcalendly.cache.room.AdventDayDao

import dagger.Module
import dagger.Provides
import javax.inject.Singleton


/**
 * Created by Rowland on 1/26/2020.
 */

@Module
class RoomModule(private val context: Context) {

    val database: AdventDatabase =
        Room.databaseBuilder(context, AdventDatabase::class.java, AdventDatabase.DB_NAME)
            .build()

    @Singleton
    @Provides
    fun providesRoomDatabase(): AdventDatabase{
        return database
    }

    @Singleton
    @Provides
    fun providesAdventDayDao(database: AdventDatabase): AdventDayDao {
        return database.adventDao()
    }
}
