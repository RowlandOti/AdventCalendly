package com.rowland.adventcalendly.di.modules

import com.rowland.adventcalendly.domain.executor.IPostExecutionThread
import com.rowland.adventcalendly.executor.UiThread
import dagger.Binds
import dagger.Module


/**
 * Created by Rowland on 1/26/2020.
 *
 * Module that provides all dependencies from the mobile package/layer and injects all activities.
 */
@Module()
abstract class UiModule {
    @Binds
    abstract fun bindPostExecutionThread(uiThread: UiThread): IPostExecutionThread
}