package com.rowland.adventcalendly.cache.mapper

import com.rowland.adventcalendly.cache.contract.IMapper
import com.rowland.adventcalendly.domain.model.AdventDay
import com.rowland.adventcalendly.presentation.AdventDayPayLoad

/**
 * Created by Rowland on 1/26/2020.
 *
 * Map a [ProductPayload] to and from a [ProductPojo] instance when data is moving between
 * this later and the Data layer
 */
object AdventDayMapper : IMapper<AdventDayPayLoad, AdventDay> {

    /**
     * Map an instance of a [AdventDayEntity] to a [AdventDay] model
     */
    override fun mapFromPresentation(model: AdventDayPayLoad): AdventDay {
        return AdventDay(
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
    override fun mapToPresentation(model: AdventDay): AdventDayPayLoad {
        return AdventDayPayLoad(
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
    override fun mapFromPresentationList(modelList: List<AdventDayPayLoad>): List<AdventDay> {
        val newList = mutableListOf<AdventDay>()
        modelList.forEach {
            newList.add(mapFromPresentation(it))
        }
        return newList
    }

    /**
     * Map an instance of a [ List<AdventDay>] to a [ List<AdventDayEntity>] model
     */
    override fun mapToPresentationList(modelList: List<AdventDay>): List<AdventDayPayLoad> {
        val newList = mutableListOf<AdventDayPayLoad>()
        modelList.forEach {
            newList.add(mapToPresentation(it))
        }
        return newList
    }

}