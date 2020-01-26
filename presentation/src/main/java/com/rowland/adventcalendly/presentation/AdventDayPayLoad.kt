package com.rowland.adventcalendly.presentation

/**
 * Created by Rowland on 1/26/2020.
 */


data class AdventDayPayLoad(
    val uid: Int = 0,
    var day: Int = 0,
    val month: String,
    var value: Int = 0,
    var isOpenable: Boolean = false,
    var isRedeemed: Boolean = false
)