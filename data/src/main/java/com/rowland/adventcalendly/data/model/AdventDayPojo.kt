package com.rowland.adventcalendly.data.model

/**
 * Created by Rowland on 1/25/2020.
 */


data class AdventDayPojo(
    val uid: Int = 0,
    var day: Int = 0,
    val month: String,
    var value: Int = 0,
    var isOpenable: Boolean = false,
    var isRedeemed: Boolean = false
)