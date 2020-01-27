package com.rowland.adventcalendly.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rowland.adventcalendly.cache.mapper.AdventDayPresentationMapper
import com.rowland.adventcalendly.domain.model.AdventDay
import com.rowland.adventcalendly.domain.usecase.adventday.BulkCreateAdventDayUseCase
import com.rowland.adventcalendly.domain.usecase.adventday.CreateAdventDayUseCase
import com.rowland.adventcalendly.domain.usecase.adventday.DeleteAllAdventDaysUseCase
import com.rowland.adventcalendly.domain.usecase.adventday.GetAllAdventDaysUseCase
import com.rowland.adventcalendly.presentation.state.Resource
import com.rowland.adventcalendly.presentation.state.ResourceState
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.subscribers.DisposableSubscriber
import javax.inject.Inject


/**
 * Created by Rowland on 1/25/2020
 */

class AdventDayViewModel @Inject
constructor(
    private val getAllAdventDayUseCase: GetAllAdventDaysUseCase,
    private val createUseCase: CreateAdventDayUseCase,
    private val bulkCreateUseCase: BulkCreateAdventDayUseCase,
    private val deleteAllUseCase: DeleteAllAdventDaysUseCase
) : ViewModel() {

    private val listOfAdventDays: MutableLiveData<Resource<List<AdventDayPayLoad>>> =
        MutableLiveData()

    fun getListOfAdventDays(): LiveData<Resource<List<AdventDayPayLoad>>> {
        return listOfAdventDays
    }

    fun loadAll() {
        listOfAdventDays.postValue(Resource(ResourceState.LOADING, null, null))
        this.getAllAdventDayUseCase.execute(AdventDaysSubscriber())
    }

    fun deleteAll(): Completable {
        return this.deleteAllUseCase.execute()
    }

    fun create(model: AdventDay): Single<Long> {
        return createUseCase.execute(CreateAdventDayUseCase.Params.forAdventDay(model))
    }

    fun bulkCreate(modelList: List<AdventDay>): Single<List<Long>> {
        return bulkCreateUseCase.execute(BulkCreateAdventDayUseCase.Params.forAdventDay(modelList))
    }

    override fun onCleared() {
        getAllAdventDayUseCase.dispose()
        super.onCleared()
    }

    inner class AdventDaysSubscriber : DisposableSubscriber<List<AdventDay>>() {
        override fun onComplete() {

        }

        override fun onNext(t: List<AdventDay>) {
            listOfAdventDays.postValue(
                Resource(
                    ResourceState.SUCCESS,
                    t.map { AdventDayPresentationMapper.mapToPresentation(it) },
                    null
                )
            )
        }

        override fun onError(exception: Throwable) {
            listOfAdventDays.postValue(Resource(ResourceState.ERROR, null, exception.message))
        }
    }
}