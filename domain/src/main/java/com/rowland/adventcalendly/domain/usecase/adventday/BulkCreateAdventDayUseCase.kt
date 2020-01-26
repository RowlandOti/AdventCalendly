package com.rowland.adventcalendly.domain.usecase.adventday

import com.rowland.adventcalendly.domain.contract.IAdventDayRepository
import com.rowland.adventcalendly.domain.executor.IPostExecutionThread
import com.rowland.adventcalendly.domain.executor.IThreadExecutor
import com.rowland.adventcalendly.domain.model.AdventDay
import com.rowland.adventcalendly.domain.usecase.SingleUseCase
import io.reactivex.Single
import javax.inject.Inject


/**
 * Created by Rowland on 1/26/2020.
 */

class BulkCreateAdventDayUseCase @Inject
constructor(
    private val repository: IAdventDayRepository,
    threadExecutor: IThreadExecutor,
    postExecutionThread: IPostExecutionThread
) : SingleUseCase<List<Long>, BulkCreateAdventDayUseCase.Params>(threadExecutor, postExecutionThread) {
    override fun buildUseCaseObservable(params: Params?): Single<List<Long>> {
        return this.repository.bulkInsert(
            params!!.modelList
        )
    }

    data class Params constructor(val modelList: List<AdventDay>) {
        companion object {
            fun forAdventDay(modelList: List<AdventDay>): Params {
                return Params(modelList)
            }
        }
    }
}