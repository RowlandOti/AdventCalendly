package com.rowland.adventcalendly.cache.mapper

import com.rowland.adventcalendly.cache.contract.IMapper
import com.rowland.adventcalendly.data.AdventDayEntity
import com.rowland.adventcalendly.data.model.AdventDayPojo

/**
 * Created by Rowland on 1/26/2020.
 *
 * Map a [ProductPayload] to and from a [ProductPojo] instance when data is moving between
 * this later and the Data layer
 */
object AdventDayMapper : IMapper<AdventDayEntity, AdventDayPojo> {

    /**
     * Map an instance of a [AdventDayEntity] to a [AdventDay] model
     */
    override fun mapFromCache(model: AdventDayEntity): AdventDayPojo {
        return AdventDayPojo(
            model.uid,
            model.day,
            model.month,
            model.value,
            model.isOpenable,
            model.isRedeemed
        )
    }

    /**
     * Map an instance of a [AdventDayEntity] to a [AdventDay] model
     */
    override fun mapToCache(model: AdventDayPojo): AdventDayEntity {
        return AdventDayEntity(
            model.uid,
            model.day,
            model.month,
            model.value,
            model.isOpenable,
            model.isRedeemed
        )
    }

    /**
     * Map an instance of a [ List<AdventDayEntity>] to a [ List<AdventDay>] model
     */
    override fun mapFromCacheList(modelList: List<AdventDayEntity>): List<AdventDayPojo> {
        val newList = mutableListOf<AdventDayPojo>()
        modelList.forEach {
            newList.add(mapFromCache(it))
        }
        return newList
    }

    /**
     * Map an instance of a [ List<AdventDay>] to a [ List<AdventDayEntity>] model
     */
    override fun mapToCacheList(modelList: List<AdventDayPojo>): List<AdventDayEntity> {
        val newList = mutableListOf<AdventDayEntity>()
        modelList.forEach {
            newList.add(mapToCache(it))
        }
        return newList
    }

}