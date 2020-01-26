package com.rowland.adventcalendly.cache.source

import com.rowland.adventcalendly.cache.mapper.AdventDayMapper
import com.rowland.adventcalendly.cache.room.AdventDayDao
import com.rowland.adventcalendly.data.contract.IAdventDayCacheSource
import com.rowland.adventcalendly.data.model.AdventDayPojo
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single
import javax.inject.Inject


/**
 * Created by Rowland on 1/26/2020.
 */
class AdventDayCacheSource @Inject constructor(private val adventDayDao: AdventDayDao) :
    IAdventDayCacheSource {
    override fun getAll(): Flowable<List<AdventDayPojo>> {
        return adventDayDao.getAll().map {
            AdventDayMapper.mapFromCacheList(it)
        }
    }

    override fun insert(model: AdventDayPojo): Single<Long> {
        return adventDayDao.insert(AdventDayMapper.mapToCache(model))
    }

    override fun bulkInsert(model: List<AdventDayPojo>): Single<List<Long>> {
        return adventDayDao.bulkInsert(AdventDayMapper.mapToCacheList(model))
    }

    override fun deleteAll(): Completable {
        return adventDayDao.deleteAll()
    }

    override fun update(model: AdventDayPojo): Single<Int> {
        return adventDayDao.update(AdventDayMapper.mapToCache(model))
    }

    override fun clearFromCache(): Completable {
       return deleteAll()
    }

    override fun isCached(): Single<Boolean> {
        return Single.just(true)
    }

    override fun isExpired(): Boolean {
        return false
    }
}