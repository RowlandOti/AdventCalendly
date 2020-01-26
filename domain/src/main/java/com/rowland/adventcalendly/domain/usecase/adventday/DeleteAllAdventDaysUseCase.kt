package com.rowland.adventcalendly.domain.usecase.adventday

import com.rowland.adventcalendly.domain.contract.IAdventDayRepository
import com.rowland.adventcalendly.domain.executor.IPostExecutionThread
import com.rowland.adventcalendly.domain.executor.IThreadExecutor
import com.rowland.adventcalendly.domain.model.AdventDay
import com.rowland.adventcalendly.domain.usecase.CompletableUseCase
import io.reactivex.Completable
import javax.inject.Inject


/**
 * Created by Rowland on 1/26/2020.
 */

class DeleteAllAdventDaysUseCase @Inject
constructor(
    private val repository: IAdventDayRepository,
    threadExecutor: IThreadExecutor,
    postExecutionThread: IPostExecutionThread
) : CompletableUseCase<DeleteAllAdventDaysUseCase.Params>(threadExecutor, postExecutionThread) {

    override fun buildUseCaseObservable(params: Params?): Completable {
        return this.repository.deleteAll()
    }

    data class Params constructor(val model: AdventDay) {
        companion object {
            fun forAdventDay(model: AdventDay): Params {
                return Params(model)
            }
        }
    }
}