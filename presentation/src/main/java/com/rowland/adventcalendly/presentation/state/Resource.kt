package com.rowland.adventcalendly.presentation.state


/**
 * Created by Rowland on 1/26/2020.
 */

open class Resource<out T> constructor(val status: ResourceState, val data: T?, val message: String?) {

    fun <T> success(data: T): Resource<T> {
        return Resource(ResourceState.SUCCESS, data, null)
    }

    fun <T> error(message: String, data: T?): Resource<T> {
        return Resource(ResourceState.ERROR, null, message)
    }

    fun <T> loading(): Resource<T> {
        return Resource(ResourceState.LOADING, null, null)
    }

}