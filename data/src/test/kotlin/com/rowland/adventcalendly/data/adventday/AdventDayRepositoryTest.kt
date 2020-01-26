package com.rowland.adventcalendly.data.adventday

import com.rowland.adventcalendly.domain.model.AdventDay
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single
import org.junit.Assert
import org.junit.Before
import org.junit.Test

import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

/**
 * Created by Rowland on 1/26/2020.
 */

@RunWith(MockitoJUnitRunner::class)
class AdventDayRepositoryTest {

    companion object {
        private const val FAKE_MONTH = "January"
        private const val FAKE_DAY = 10
        private const val FAKE_VALUE = 100
    }

    lateinit var adventDayList: ArrayList<AdventDay>

    @Mock
    private lateinit var repository: AdventDayRepository



    @Before
    fun setUp() {
        adventDayList = ArrayList<AdventDay>()
        adventDayList.add(AdventDay(month= FAKE_MONTH))
        adventDayList.add(AdventDay(month= FAKE_MONTH))
        adventDayList.add(AdventDay(month= FAKE_MONTH))
    }


    @Test
    fun getAll() {
        Mockito.doReturn(Flowable.fromArray(adventDayList)).`when`(repository).getAll()

        val testSubscriber = repository.getAll().test()
        testSubscriber.awaitTerminalEvent()
        testSubscriber.assertComplete()
        testSubscriber.assertNoErrors()
        testSubscriber.assertValueCount(1)

        Assert.assertTrue(testSubscriber.values()[0].size.equals(adventDayList.size))
        Assert.assertTrue(testSubscriber.values()[0].containsAll(adventDayList))

        Mockito.verify(repository).getAll()
    }

    @Test
    fun insert() {
        val mockProduct = AdventDay(month= FAKE_MONTH)
        mockProduct.day = FAKE_DAY
        Mockito.doReturn(Single.just(12)).`when`(repository).insert(mockProduct)

        val testSubscriber = repository.insert(mockProduct).test()
        testSubscriber.awaitTerminalEvent()
        testSubscriber.assertComplete()
        testSubscriber.assertNoErrors()

        Assert.assertNotEquals(testSubscriber.values()[0], -1)

        Mockito.verify(repository).insert(mockProduct)
    }

    @Test
    fun bulkInsert() {
        Mockito.doReturn(Single.just(arrayListOf(12,15,16))).`when`(repository).bulkInsert(adventDayList)

        val testSubscriber = repository.bulkInsert(adventDayList).test()
        testSubscriber.awaitTerminalEvent()
        testSubscriber.assertComplete()
        testSubscriber.assertNoErrors()

        Assert.assertEquals(testSubscriber.values()[0].size, adventDayList.size)

        Mockito.verify(repository).bulkInsert(adventDayList)
    }

    @Test
    fun deleteAll() {
        val mockProduct = AdventDay(month= FAKE_MONTH)
        mockProduct.day = FAKE_DAY

        Mockito.doReturn(Completable.complete()).`when`(repository).deleteAll()

        val testSubscriber = repository.deleteAll().test()
        testSubscriber.awaitTerminalEvent()
        testSubscriber.assertComplete()
        testSubscriber.assertNoErrors()

        Mockito.verify(repository).deleteAll()
    }

    @Test
    fun update() {
        val mockProduct = AdventDay(month= FAKE_MONTH)
        mockProduct.day = FAKE_DAY
        mockProduct.value = FAKE_VALUE


        Mockito.doReturn(Single.just(14)).`when`(repository).update(mockProduct)

        val testSubscriber = repository.update(mockProduct).test()
        testSubscriber.awaitTerminalEvent()
        testSubscriber.assertComplete()
        testSubscriber.assertNoErrors()

        Assert.assertNotEquals(testSubscriber.values()[0], -1)

        Mockito.verify(repository).update(mockProduct)
    }
}