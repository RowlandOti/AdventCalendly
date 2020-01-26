package com.rowland.adventcalendly

import android.annotation.SuppressLint
import android.app.Application
import com.rowland.adventcalendly.data.AdventDatabase
import com.rowland.adventcalendly.data.AdventDayEntity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import java.util.*
import kotlin.collections.ArrayList


/**
 * Created by Rowland on 1/25/2020.
 */
class AdventApp : Application() {

    internal lateinit var database: AdventDatabase

    init {
        INSTANCE = this
    }

    override fun onCreate() {
        super.onCreate()
        database = AdventDatabase.getInstance(this)

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

    @SuppressLint("CheckResult")
    fun initDataOnStartUp() {
        val items = ArrayList<AdventDayEntity>()

        val calendar = Calendar.getInstance()
        //calendar.add(Calendar.DATE, -7)
        val dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH)
        val monthStr = DateUtils.getMonth(Locale.getDefault(), calendar.get(Calendar.MONTH))

        for (i in 1 until 24) {
            val adventDay = AdventDayEntity(month = monthStr)
            adventDay.day = i

            Timber.d("Day is: $i and today is $dayOfMonth")
            adventDay.isOpenable = i <= dayOfMonth
            items.add(adventDay)
        }

       database.adventDao().bulkInsert(items)
            .subscribeOn(Schedulers.io())
            .observeOn (AndroidSchedulers.mainThread())
            .subscribe({
                Timber.d(it.toString())
            }, {
                Timber.d(it.toString())
            })
    }

    companion object {
        lateinit var INSTANCE: AdventApp
    }

}