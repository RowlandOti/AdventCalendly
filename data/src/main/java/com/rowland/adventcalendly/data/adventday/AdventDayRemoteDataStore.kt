package com.rowland.adventcalendly.data.adventday

import com.rowland.adventcalendly.data.contract.IAdventDayDataStore
import com.rowland.adventcalendly.data.contract.IAdventDayRemoteSource
import com.rowland.adventcalendly.domain.model.AdventDay
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single
import javax.inject.Inject


/**
 * Created by Rowland on 1/26/2020.
 */
class AdventDayRemoteDataStore @Inject constructor(private val dataSource: IAdventDayRemoteSource) :
    IAdventDayDataStore {

    override fun getAll(): Flowable<List<AdventDay>> {
        return dataSource.getAll()
    }

    override fun insert(model: AdventDay): Single<Long> {
        return dataSource.insert(model)
    }

    override fun bulkInsert(modelList: List<AdventDay>): Single<List<Long>> {
        return dataSource.bulkInsert(modelList)
    }

    override fun deleteAll(): Completable {
        return dataSource.deleteAll()
    }

    override fun update(model: AdventDay): Single<Int> {
        return dataSource.update(model)
    }

    override fun clearFromCache(): Completable {
        throw UnsupportedOperationException()
    }

    override fun isCached(): Single<Boolean> {
        throw UnsupportedOperationException()
    }
}