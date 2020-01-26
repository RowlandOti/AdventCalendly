package com.rowland.adventcalendly

import android.annotation.SuppressLint
import android.app.Application
import com.rowland.adventcalendly.cache.room.AdventDatabase
import com.rowland.adventcalendly.data.AdventDayEntity
import com.rowland.adventcalendly.di.component.AppComponent
import com.rowland.adventcalendly.di.modules.AppModule
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import java.util.*
import kotlin.collections.ArrayList


/**
 * Created by Rowland on 1/25/2020.
 */
class AdventApp : Application() {

    lateinit var appComponent: AppComponent
        private set

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

        appComponent = DaggerApplicationComponent.builder()
            .contextModule(AppModule(this))
            .build()

        initDataOnStartUp()
    }

    @SuppressLint("CheckResult")
    fun initDataOnStartUp() {
        val items = ArrayList<AdventDayEntity>()

        val calendar = Calendar.getInstance()
        // Minus -7 , so that we can simulate openable days of the month - assume today is 7 days back
        calendar.add(Calendar.DATE, -10)
        val dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH)
        val monthStr = DateUtils.getMonth(Locale.getDefault(), calendar.get(Calendar.MONTH))

        for (i in 1 until 25) {
            val adventDay = AdventDayEntity(month = monthStr)
            adventDay.day = i
            adventDay.value = i* 10 * (0..5).random()
            adventDay.isOpenable = i <= dayOfMonth
            items.add(adventDay)
        }

        Timber.d("Today is $dayOfMonth")

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