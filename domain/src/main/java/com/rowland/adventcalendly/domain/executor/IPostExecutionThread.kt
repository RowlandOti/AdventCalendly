package com.rowland.adventcalendly.domain.executor

import io.reactivex.Scheduler


/**
 * Created by Rowland on 1/26/2020.
 *
 * Thread abstraction created to change the execution context from any thread to any other thread.
 * Useful to encapsulate a UI Thread for example, since some job will be done in background, an
 * implementation of this interface will change context and update the UI.
 */
interface IPostExecutionThread {
    val scheduler: Scheduler
}