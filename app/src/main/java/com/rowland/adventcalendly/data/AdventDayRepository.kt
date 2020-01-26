package com.rowland.adventcalendly.data

import android.app.Application
import androidx.lifecycle.LiveData
import com.rowland.adventcalendly.AdventApp
import io.reactivex.Completable
import io.reactivex.Single


/**
 * Created by Rowland on 1/25/2020.
 */
class AdventDayRepository() {

    fun insert(model: AdventDayEntity): Single<Long> {
        return AdventApp.INSTANCE.database.adventDao().insert(model)
    }

    fun deleteAll(): Completable {
        return AdventApp.INSTANCE.database.adventDao().deleteAll()
    }

    fun getAll(): LiveData<List<AdventDayEntity>> {
        return AdventApp.INSTANCE.database.adventDao().getAll()
    }
}