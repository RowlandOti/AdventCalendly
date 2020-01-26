package com.rowland.adventcalendly.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.rowland.adventcalendly.data.AdventDayEntity
import com.rowland.adventcalendly.data.AdventDayRepository


/**
 * Created by Rowland on 1/25/2020.
 */
class AdventDayViewModel : ViewModel() {

    private var repository: AdventDayRepository = AdventDayRepository()
    private val listOfAdventDays: LiveData<List<AdventDayEntity>> = repository.getAll()

    fun getListOfAdventDays(): LiveData<List<AdventDayEntity>> {
        return listOfAdventDays
    }
}