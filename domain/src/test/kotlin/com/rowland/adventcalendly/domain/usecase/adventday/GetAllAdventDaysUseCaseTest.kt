package com.rowland.adventcalendly.domain.usecase.adventday

import com.rowland.adventcalendly.domain.contract.IAdventDayRepository
import com.rowland.adventcalendly.domain.executor.IPostExecutionThread
import com.rowland.adventcalendly.domain.executor.IThreadExecutor
import com.rowland.adventcalendly.domain.model.AdventDay
import io.reactivex.Flowable
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

/**
 * Created by Rowland on 1/26/2020.
 */
@RunWith(MockitoJUnitRunner::class)
class GetAllAdventDaysUseCaseTest {
    companion object {
        private const val FAKE_MONTH = "January"
        private const val FAKE_DAY = 10
    }

    lateinit var adventDayList: ArrayList<AdventDay>

    @Mock
    private lateinit var threadExecutor: IThreadExecutor
    @Mock
    private lateinit var postExecutionThread: IPostExecutionThread
    @Mock
    private lateinit var repository: IAdventDayRepository
    @InjectMocks
    private lateinit var getAllAdventDaysUseCase: GetAllAdventDaysUseCase


    @Before
    fun setUp() {
        adventDayList = ArrayList<AdventDay>()
        adventDayList.add(AdventDay(month= FAKE_MONTH))
        adventDayList.add(AdventDay(month= FAKE_MONTH))
        adventDayList.add(AdventDay(month= FAKE_MONTH))
    }

    @Test
    fun buildUseCaseObservable() {
        Mockito.doReturn(Flowable.fromArray(adventDayList)).`when`(repository).getAll()


        val testSubscriber = getAllAdventDaysUseCase.buildUseCaseObservable().test()
        testSubscriber.awaitTerminalEvent()
        testSubscriber.assertComplete()
        testSubscriber.assertNoErrors()
        testSubscriber.assertValueCount(1)

        Assert.assertTrue(testSubscriber.values()[0].size == adventDayList.size)
        Assert.assertTrue(testSubscriber.values()[0].containsAll(adventDayList))

        val adventDayList2 = ArrayList<AdventDay>()
        val adventDay = AdventDay(month= FAKE_MONTH)
        adventDay.day = FAKE_DAY
        adventDayList2.add(adventDay)
        Assert.assertTrue(!testSubscriber.values()[0].containsAll(adventDayList2))

        Mockito.verify(repository).getAll()
    }
}