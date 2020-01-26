package com.rowland.adventcalendly.features.di.component

import com.rowland.adventcalendly.features.calendar.ui.CalendarFragment
import com.rowland.adventcalendly.features.calendar.ui.MainActivity
import com.rowland.adventcalendly.features.di.modules.CalendarModule
import dagger.Component
import javax.inject.Singleton


/**
 * Created by Rowland on 1/26/2020.
 */
@Component(modules = [CalendarModule::class])
@Singleton
interface CalendarComponent {
    // Activities
    fun inject(mainActivity: MainActivity)

    // Fragments
    fun inject(calendarFragment: CalendarFragment)
}
