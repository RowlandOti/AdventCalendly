package com.rowland.adventcalendly.di.modules

import com.rowland.adventcalendly.data.adventday.AdventDayRepository
import com.rowland.adventcalendly.data.executor.JobExecutor
import com.rowland.adventcalendly.domain.contract.IAdventDayRepository
import com.rowland.adventcalendly.domain.executor.IThreadExecutor
import dagger.Binds
import dagger.Module


/**
 * Created by Rowland on 1/26/2020.
 */
@Module()
abstract class DataModule {

    @Binds
    internal abstract fun bindAdventDayRepository(modelRepository: AdventDayRepository): IAdventDayRepository

    @Binds
    abstract fun bindThreadExecutor(jobExecutor: JobExecutor): IThreadExecutor
}