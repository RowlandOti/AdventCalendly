package com.rowland.adventcalendly.di.component

import android.content.Context
import com.rowland.adventcalendly.di.modules.AppModule
import dagger.Component


/**
 * Created by Rowland on 1/26/2020.
 */

@Component(modules = [AppModule::class])
interface AppComponent {

    val context: Context
}