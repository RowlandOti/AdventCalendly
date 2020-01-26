package com.rowland.adventcalendly.data.contract


/**
 * Created by Rowland on 1/26/2020.
 *
 * Interface for model mappers. It provides helper methods that facilitate
 * retrieving of models from outer data source layers
 *
 * @param <M> the remote model input type
 * @param <E> the entity model output type
 */
interface IDataMapper<M, E> {
    fun mapFromData(model: M): E
    fun mapToData(model: E): M
    fun mapToDataList(modelList: List<E>): List<M>
    fun mapFromDataList(modelList: List<M>): List<E>
}