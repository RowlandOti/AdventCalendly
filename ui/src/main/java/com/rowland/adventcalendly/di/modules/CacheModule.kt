package com.rowland.adventcalendly.di.modules

import com.rowland.adventcalendly.cache.source.AdventDayCacheSource
import com.rowland.adventcalendly.data.contract.IAdventDayCacheSource
import dagger.Binds
import dagger.Module


/**
 * Created by Rowland on 1/26/2020.
 */

@Module()
abstract class CacheModule {

    @Binds
    internal abstract fun bindAdventDayCacheSource(modelCache: AdventDayCacheSource): IAdventDayCacheSource
}