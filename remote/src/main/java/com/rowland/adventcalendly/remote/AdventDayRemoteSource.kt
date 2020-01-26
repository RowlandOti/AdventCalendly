package com.rowland.adventcalendly.remote

import com.rowland.adventcalendly.data.contract.IAdventDayRemoteSource
import com.rowland.adventcalendly.data.model.AdventDayPojo
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single
import javax.inject.Inject


/**
 * Created by Rowland on 1/26/2020.
 */
class AdventDayRemoteSource @Inject constructor() : IAdventDayRemoteSource {

    override fun getAll(): Flowable<List<AdventDayPojo>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun insert(model: AdventDayPojo): Single<Long> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun bulkInsert(model: List<AdventDayPojo>): Single<List<Long>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun deleteAll(): Completable {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun update(model: AdventDayPojo): Single<Int> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}