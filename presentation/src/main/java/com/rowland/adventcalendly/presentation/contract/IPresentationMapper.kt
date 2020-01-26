package com.rowland.adventcalendly.cache.contract


/**
 * Created by Rowland on 1/26/2020.
 *
 * Interface for model mappers. It provides helper methods that facilitate
 * retrieving of models from outer data source layers
 *
 * @param <M> the remote model input type
 * @param <E> the entity model output type
 */
interface IPresentationMapper<M, E> {
    fun mapFromPresentation(model: M): E
    fun mapToPresentation(model: E): M
    fun mapToPresentationList(modelList: List<E>): List<M>
    fun mapFromPresentationList(modelList: List<M>): List<E>
}