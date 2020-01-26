package com.rowland.adventcalendly.domain.usecase

import com.rowland.adventcalendly.domain.executor.IPostExecutionThread
import com.rowland.adventcalendly.domain.executor.IThreadExecutor
import io.reactivex.Flowable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subscribers.DisposableSubscriber


/**
 * Created by Rowland on 1/26/2020.
 *
 * Abstract class for a UseCase that returns an instance of a [Flowable].
 */
abstract class FlowableUseCase<T, in Params> constructor(
    private val threadExecutor: IThreadExecutor,
    private val postExecutionThread: IPostExecutionThread
) {

    private val disposables = CompositeDisposable()

    /**
     * Builds a [Single] which will be used when the current [FlowableUseCase] is executed.
     */
    protected abstract fun buildUseCaseObservable(params: Params? = null): Flowable<T>

    /**
     * Executes the current use case.
     */
    open fun execute(observer: DisposableSubscriber<T>, params: Params? = null) {
        val observable = this.buildUseCaseObservable(params)
            .subscribeOn(Schedulers.single())
            .observeOn(postExecutionThread.scheduler) as Flowable<T>
        addDisposable(observable.subscribeWith(observer))
    }

    /**
     * Dispose from current [CompositeDisposable].
     */
    fun dispose() {
        if (!disposables.isDisposed) disposables.dispose()
    }

    /**
     * Dispose from current [CompositeDisposable].
     */
    private fun addDisposable(disposable: Disposable) {
        disposables.add(disposable)
    }
}