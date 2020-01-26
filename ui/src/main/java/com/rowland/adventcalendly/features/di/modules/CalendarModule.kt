package com.rowland.adventcalendly.features.di.modules

import com.rowland.adventcalendly.di.modules.AppModule
import com.rowland.adventcalendly.di.modules.CacheModule
import com.rowland.adventcalendly.di.modules.DataModule
import com.rowland.adventcalendly.di.modules.DomainModule
import com.rowland.adventcalendly.di.modules.PresentationModule
import com.rowland.adventcalendly.di.modules.RoomModule
import com.rowland.adventcalendly.di.modules.UiModule
import com.rowland.adventcalendly.features.calendar.ui.CalendarAdapter
import dagger.Module
import dagger.Provides


/**
 * Created by Rowland on 1/26/2020.
 */

@Module(
    includes = arrayOf(
        RoomModule::class,
        AppModule::class,
        PresentationModule::class,
        DataModule::class,
        UiModule::class,
        DataModule::class,
        DomainModule::class,
        PresentationModule::class,
        CacheModule::class
    )
)
class CalendarModule {

    @Provides
    internal fun providesCalendarAdapter(): CalendarAdapter {
        return CalendarAdapter()
    }

}