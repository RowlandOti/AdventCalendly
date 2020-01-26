package com.rowland.adventcalendly.data

import android.provider.ContactsContract.CommonDataKinds.Note
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single


@Dao
interface AdventDayDao {

    @Query("SELECT * FROM advent_day WHERE uid = :id")
    fun getById(id: String): LiveData<AdventDayEntity>

    @Query("SELECT * FROM advent_day WHERE day = :day")
    fun getByDay(day: Int): LiveData<AdventDayEntity>

    @Query("SELECT * FROM advent_day")
    fun getAll(): LiveData<List<AdventDayEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(model: AdventDayEntity): Single<Long>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun bulkInsert(model: List<AdventDayEntity>): Single<List<Long>>

    @Query("DELETE FROM advent_day")
    fun deleteAll(): Completable

    @Delete
    fun delete(model: AdventDayEntity): Completable

    @Query("DELETE FROM advent_day WHERE uid = :id")
    fun delete(id: Long): Completable

    @Update
    fun update(model: AdventDayEntity): Single<Int>

    @Query("SELECT COUNT(uid) FROM advent_day")
    fun getCount(): LiveData<Int>

}
