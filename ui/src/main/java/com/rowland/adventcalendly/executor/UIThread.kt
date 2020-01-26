package com.rowland.adventcalendly.executor

import com.rowland.adventcalendly.domain.executor.IPostExecutionThread
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import javax.inject.Inject


/**
 * Created by Rowland on 1/26/2020.
 *
 * MainThread (UI Thread) implementation based on a [Scheduler]
 * which will execute actions on the Android UI thread
 */
class UiThread @Inject internal constructor() : IPostExecutionThread {

    override val scheduler: Scheduler
        get() = AndroidSchedulers.mainThread()

}