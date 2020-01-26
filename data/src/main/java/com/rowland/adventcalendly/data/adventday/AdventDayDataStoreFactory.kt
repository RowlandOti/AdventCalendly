package com.rowland.adventcalendly.data.adventday

import com.rowland.adventcalendly.data.contract.IAdventDayCacheSource
import com.rowland.adventcalendly.data.contract.IAdventDayDataStore
import javax.inject.Inject


class AdventDayDataStoreFactory @Inject constructor(
    private val productCacheSource: IAdventDayCacheSource,
    private val productCacheDataStore: AdventDayCacheDataStore,
    private val productRemoteDataStore: AdventDayRemoteDataStore
) {

    /**
     * Returns a DataStore based on whether or not there is content in the cache and the cache
     * has not expired
     */
    fun retrieveDataStore(isCached: Boolean): IAdventDayDataStore {
        if (isCached && !productCacheSource.isExpired()) {
            return retrieveCacheDataStore()
        }
        return retrieveRemoteDataStore()
    }

    /**
     * Return an instance of the Cache Data Store
     */
    fun retrieveCacheDataStore(): IAdventDayDataStore {
        return productCacheDataStore
    }

    /**
     * Return an instance of the Remote Data Store
     */
    fun retrieveRemoteDataStore(): IAdventDayDataStore {
        return productRemoteDataStore
    }
}

