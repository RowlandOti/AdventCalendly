package com.rowland.adventcalendly.domain.usecase

import com.rowland.adventcalendly.domain.executor.IPostExecutionThread
import com.rowland.adventcalendly.domain.executor.IThreadExecutor
import io.reactivex.Single
import io.reactivex.disposables.Disposables
import io.reactivex.schedulers.Schedulers


/**
 * Created by Rowland on 1/26/2020.
 *
 * Abstract class for a UseCase that returns an instance of a [Single].
 * */

abstract class SingleUseCase<T, in Params> constructor(
    private val threadExecutor: IThreadExecutor,
    private val postExecutionThread: IPostExecutionThread
) {

    private val subscription = Disposables.empty()

    /**
     * Builds a [Single] which will be used when the current [SingleUseCase] is executed.
     */
    protected abstract fun buildUseCaseObservable(params: Params? = null): Single<T>

    /**
     * Executes the current use case.
     */
    open fun execute(params: Params? = null): Single<T> {
        return this.buildUseCaseObservable(params)
            .subscribeOn(Schedulers.from(threadExecutor))
            .observeOn(postExecutionThread.scheduler)
    }

    /**
     * Unsubscribes from current [Disposable].
     */
    fun unsubscribe() {
        if (!subscription.isDisposed) {
            subscription.dispose()
        }
    }
}