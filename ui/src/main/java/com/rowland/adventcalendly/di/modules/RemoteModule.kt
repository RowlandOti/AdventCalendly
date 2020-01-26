package com.rowland.adventcalendly.di.modules

import com.rowland.adventcalendly.data.contract.IAdventDayRemoteSource
import com.rowland.adventcalendly.remote.AdventDayRemoteSource
import dagger.Binds
import dagger.Module


/**
 * Created by Rowland on 1/26/2020.
 */

@Module()
abstract class RemoteModule {

    @Binds
    internal abstract fun bindAdventDayRemoteSource(modelCache: AdventDayRemoteSource): IAdventDayRemoteSource
}