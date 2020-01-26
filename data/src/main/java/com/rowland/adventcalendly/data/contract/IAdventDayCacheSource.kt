package com.rowland.adventcalendly.data.contract

import com.rowland.adventcalendly.domain.model.AdventDay
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single

interface IAdventDayCacheSource {
    fun getAll(): Flowable<List<AdventDay>>
    fun insert(model: AdventDay): Single<Long>
    fun bulkInsert(model: List<AdventDay>): Single<List<Long>>
    fun deleteAll(): Completable
    fun update(model: AdventDay): Single<Int>
    fun clearFromCache(): Completable
    fun isCached(): Single<Boolean>
    fun isExpired(): Boolean
}