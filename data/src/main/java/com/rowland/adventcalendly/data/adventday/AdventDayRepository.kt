package com.rowland.adventcalendly.data.adventday

import com.rowland.adventcalendly.data.mapper.AdventDayMapper
import com.rowland.adventcalendly.domain.contract.IAdventDayRepository
import com.rowland.adventcalendly.domain.model.AdventDay
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single
import javax.inject.Inject


/**
 * Created by Rowland on 1/26/2020.
 */
class AdventDayRepository @Inject constructor(private val dataStoreFactory: AdventDayDataStoreFactory) :
    IAdventDayRepository {

    override fun getAll(): Flowable<List<AdventDay>> {
        return dataStoreFactory.retrieveCacheDataStore().isCached()
            .flatMapPublisher { dataStoreFactory.retrieveDataStore(it).getAll() }
            .flatMap { Flowable.just(it.map { AdventDayMapper.mapFromData(it) }) }
    }

    override fun insert(model: AdventDay): Single<Long> {
        return dataStoreFactory.retrieveDataStore(true).insert(AdventDayMapper.mapToData(model))
    }

    override fun bulkInsert(modelList: List<AdventDay>): Single<List<Long>> {
        return dataStoreFactory.retrieveDataStore(true).bulkInsert(AdventDayMapper.mapToDataList(modelList))
    }

    override fun deleteAll(): Completable {
        return dataStoreFactory.retrieveDataStore(true).deleteAll()
    }

    override fun update(model: AdventDay): Single<Int> {
        return dataStoreFactory.retrieveDataStore(true).update(AdventDayMapper.mapToData(model))
    }
}