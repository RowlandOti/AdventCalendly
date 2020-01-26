package com.rowland.adventcalendly.data.contract

import com.rowland.adventcalendly.data.model.AdventDayPojo
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single

interface IAdventDayDataStore {
    fun getAll(): Flowable<List<AdventDayPojo>>
    fun insert(model: AdventDayPojo): Single<Long>
    fun bulkInsert(modelList: List<AdventDayPojo>): Single<List<Long>>
    fun deleteAll(): Completable
    fun update(model: AdventDayPojo): Single<Int>
    fun clearFromCache(): Completable
    fun isCached(): Single<Boolean>
}