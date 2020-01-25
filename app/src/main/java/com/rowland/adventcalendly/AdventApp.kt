package com.rowland.adventcalendly

import android.app.Application
import com.rowland.adventcalendly.data.AdventDatabase
import timber.log.Timber


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

    companion object {
        lateinit var INSTANCE: AdventApp
    }

}