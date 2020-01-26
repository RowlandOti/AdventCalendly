package com.rowland.adventcalendly.di.modules

import android.content.Context
import dagger.Module
import dagger.Provides


/**
 * Created by Rowland on 1/26/2020.
 */

@Module
class AppModule(private val context: Context) {

    @Provides
    fun providesContext(): Context {
        return this.context
    }
}
