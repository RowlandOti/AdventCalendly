package com.rowland.adventcalendly.presentation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.KArgumentCaptor
import com.nhaarman.mockitokotlin2.anyOrNull
import com.nhaarman.mockitokotlin2.argumentCaptor
import com.nhaarman.mockitokotlin2.eq
import com.rowland.adventcalendly.cache.mapper.AdventDayPresentationMapper
import com.rowland.adventcalendly.domain.executor.IPostExecutionThread
import com.rowland.adventcalendly.domain.executor.IThreadExecutor
import com.rowland.adventcalendly.domain.model.AdventDay
import com.rowland.adventcalendly.domain.usecase.adventday.BulkCreateAdventDayUseCase
import com.rowland.adventcalendly.domain.usecase.adventday.CreateAdventDayUseCase
import com.rowland.adventcalendly.domain.usecase.adventday.DeleteAllAdventDaysUseCase
import com.rowland.adventcalendly.domain.usecase.adventday.GetAllAdventDaysUseCase
import com.rowland.adventcalendly.presentation.state.Resource
import com.rowland.adventcalendly.presentation.state.ResourceState
import io.reactivex.subscribers.DisposableSubscriber
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations
import org.mockito4kotlin.annotation.KCaptor
import java.lang.RuntimeException


/**
 * Created by Rowland on 1/26/2020.
 */
@RunWith(JUnit4::class)
class AdventDayViewModelTest {

    companion object {
        private const val FAKE_MONTH = "January"
        private const val FAKE_DAY = 10
        private const val FAKE_ERROR_MSG = "This test failed"
    }

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    lateinit var adventDayList: ArrayList<AdventDay>

    @Mock
    lateinit var getAllAdventDaysUseCase: GetAllAdventDaysUseCase
    @Mock
    lateinit var createUseCase: CreateAdventDayUseCase
    @Mock
    lateinit var bulkCreateUseCase: BulkCreateAdventDayUseCase
    @Mock
    lateinit var deleteAllUseCase: DeleteAllAdventDaysUseCase
    @Mock
    lateinit var observer: Observer<Resource<List<AdventDayPayLoad>>>
    @Mock
    private lateinit var threadExecutor: IThreadExecutor
    @Mock
    private lateinit var postExecutionThread: IPostExecutionThread

    @KCaptor
    private lateinit var captor: KArgumentCaptor<DisposableSubscriber<List<AdventDay>>>

    lateinit var viewModel: AdventDayViewModel


    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        captor = argumentCaptor()
        viewModel = AdventDayViewModel(
            getAllAdventDaysUseCase,
            createUseCase,
            bulkCreateUseCase,
            deleteAllUseCase
        )

        adventDayList = ArrayList<AdventDay>()
        adventDayList.add(AdventDay(month = FAKE_MONTH))
        adventDayList.add(AdventDay(month = FAKE_MONTH))
        adventDayList.add(AdventDay(month = FAKE_MONTH))
    }

    @Test
    fun getListOfAdventDays() {

        viewModel.getListOfAdventDays().observeForever(observer)
        viewModel.loadAll()

        verify(getAllAdventDaysUseCase, times(1)).execute(anyOrNull(), anyOrNull())
    }

    @Test
    fun loadAllSuccess() {
        viewModel.loadAll()

        verify(getAllAdventDaysUseCase).execute(captor.capture(), eq(null))
        captor.firstValue.onNext(adventDayList)

        assert(viewModel.getListOfAdventDays().value?.status == ResourceState.SUCCESS)
    }

    @Test
    fun loadAllSuccessWithData() {
        viewModel.loadAll()

        verify(getAllAdventDaysUseCase).execute(captor.capture(), eq(null))
        captor.firstValue.onNext(adventDayList)

        assert(
            viewModel.getListOfAdventDays().value?.data == AdventDayPresentationMapper.mapToPresentationList(
                adventDayList
            )
        )
    }

    @Test
    fun loadAllSuccessWithNoMessage() {
        viewModel.loadAll()

        verify(getAllAdventDaysUseCase).execute(captor.capture(), eq(null))
        captor.firstValue.onNext(adventDayList)

        assert(viewModel.getListOfAdventDays().value?.message == null)
    }

    @Test
    fun loadAllLoading() {
        viewModel.loadAll()

        assert(viewModel.getListOfAdventDays().value?.status == ResourceState.LOADING)
    }

    @Test
    fun loadAllFailsWithMessage() {

        viewModel.loadAll()

        verify(getAllAdventDaysUseCase).execute(captor.capture(), eq(null))
        captor.firstValue.onError(RuntimeException(FAKE_ERROR_MSG))

        assert(viewModel.getListOfAdventDays().value?.message == FAKE_ERROR_MSG)
    }
}