package com.rowland.adventcalendly.domain.usecase.adventday

import com.rowland.adventcalendly.domain.contract.IAdventDayRepository
import com.rowland.adventcalendly.domain.executor.IPostExecutionThread
import com.rowland.adventcalendly.domain.executor.IThreadExecutor
import com.rowland.adventcalendly.domain.model.AdventDay
import com.rowland.adventcalendly.domain.usecase.FlowableUseCase
import io.reactivex.Flowable
import javax.inject.Inject


/**
 * Created by Rowland on 1/26/2020.
 */

class GetAllAdventDaysUseCase @Inject
constructor(
    private val repository: IAdventDayRepository,
    threadExecutor: IThreadExecutor,
    postExecutionThread: IPostExecutionThread
) : FlowableUseCase<List<AdventDay>, GetAllAdventDaysUseCase.Params>(
    threadExecutor,
    postExecutionThread
) {

    public override fun buildUseCaseObservable(params: Params?): Flowable<List<AdventDay>> {
        return this.repository.getAll()
    }

    // Use this to pass in any parameters you may want e.g if you want to retrieve by certain criterias
    data class Params constructor(val userUID: String) {
        companion object {
            fun forAdventDay(userUID: String): Params {
                return Params(userUID)
            }
        }
    }
}