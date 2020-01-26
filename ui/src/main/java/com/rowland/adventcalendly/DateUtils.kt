package com.rowland.adventcalendly

import java.text.DateFormatSymbols
import java.util.*

/**
 * Created by Rowland on 1/25/2020.
 */
object DateUtils {
    fun getMonth(locale: Locale, month: Int): String {
        return DateFormatSymbols(locale).months[month]
    }
}