package com.example.depoptask.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.depoptask.network.*
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.rxjava3.android.plugins.RxAndroidPlugins
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.plugins.RxJavaPlugins
import io.reactivex.rxjava3.schedulers.Schedulers
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

class PopularItemsViewModelTest {
    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()
    private val shopItems: ShopItems = ShopItems(
        listOf(
            ShopItem(
                id = 1,
                userID = 2,
                description = "description",
                listOf(
                    PicturesData(
                        id = 0, Formats(
                            P1 = Format(url = "www.test.com", 10, 10),
                            P2 = Format(url = "www.test.com", 10, 10),
                            P4 = Format(url = "www.test.com", 10, 10),
                            P5 = Format(url = "www.test.com", 10, 10),
                            P6 = Format(url = "www.test.com", 10, 10),
                            P7 = Format(url = "www.test.com", 10, 10),
                            P8 = Format(url = "www.test.com", 10, 10),
                        )
                    )
                )
            )
        )
    )

    @Before
    fun setUp() {
        RxJavaPlugins.setIoSchedulerHandler { Schedulers.trampoline() }
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }
    }

    @Test
    fun test() {
        val mockApi = mock<DepopApiService>()
        whenever(mockApi.getPopularItems()).thenReturn(Single.just(shopItems))
        val viewModel = PopularItemsViewModel(mockApi)
        assertEquals(1, viewModel.shopItems.value?.size)
    }
}