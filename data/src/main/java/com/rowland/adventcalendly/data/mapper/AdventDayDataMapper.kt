package com.rowland.adventcalendly.data.mapper


import com.rowland.adventcalendly.data.contract.IDataMapper
import com.rowland.adventcalendly.data.model.AdventDayPojo
import com.rowland.adventcalendly.domain.model.AdventDay

/**
 * Created by Rowland on 1/26/2020.
 *
 * Map a [ProductPayload] to and from a [ProductPojo] instance when data is moving between
 * this later and the Data layer
 */
object AdventDayDataMapper : IDataMapper<AdventDayPojo, AdventDay> {

    /**
     * Map an instance of a [AdventDayPojo] to a [AdventDay] model
     */
    override fun mapFromData(model: AdventDayPojo): AdventDay {
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
     * Map an instance of a [AdventDayPojo] to a [AdventDay] model
     */
    override fun mapToData(model: AdventDay): AdventDayPojo {
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
     * Map an instance of a [ List<AdventDayPojo>] to a [ List<AdventDay>] model
     */
    override fun mapFromDataList(modelList: List<AdventDayPojo>): List<AdventDay> {
        val newList = mutableListOf<AdventDay>()
        modelList.forEach {
            newList.add(mapFromData(it))
        }
        return newList
    }

    /**
     * Map an instance of a [ List<AdventDay>] to a [ List<AdventDayEntity>] model
     */
    override fun mapToDataList(modelList: List<AdventDay>): List<AdventDayPojo> {
        val newList = mutableListOf<AdventDayPojo>()
        modelList.forEach {
            newList.add(mapToData(it))
        }
        return newList
    }

}