package com.rowland.adventcalendly.cache.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.rowland.adventcalendly.data.AdventDayEntity
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single


@Dao
interface AdventDayDao {

    @Query("SELECT * FROM advent_day")
    fun getAll(): Flowable<List<AdventDayEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(model: AdventDayEntity): Single<Long>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun bulkInsert(model: List<AdventDayEntity>): Single<List<Long>>

    @Query("DELETE FROM advent_day")
    fun deleteAll(): Completable

    @Update
    fun update(model: AdventDayEntity): Single<Int>
}
