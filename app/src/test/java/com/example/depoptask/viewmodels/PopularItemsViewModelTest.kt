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
import org.junit.Assert.assertNull
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

    private lateinit var viewModel: PopularItemsViewModel

    @Before
    fun setUp() {
        RxJavaPlugins.setIoSchedulerHandler { Schedulers.trampoline() }
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }

        val mockApi = mock<DepopApiService>()
        whenever(mockApi.getPopularItems()).thenReturn(Single.just(shopItems))
        viewModel = PopularItemsViewModel(mockApi)
    }

    @Test
    fun `initializing a PopularItemsViewModel fetches shopItems`() {
        assertEquals(1, viewModel.shopItems.value?.size)
    }

    @Test
    fun `when item is chosen then navigateWithSelectedItem takes the value of the chosen item`(){
        val shopItem = shopItems.objects[0]
        viewModel.chooseItem(shopItem = shopItem)

        assertEquals(shopItem, viewModel.navigateWithSelectedItem.value)
    }

    @Test
    fun `given item is chosen when calling clearItemChosen(), navigateWithSelectedItem is cleared`(){
        val shopItem = shopItems.objects[0]
        viewModel.chooseItem(shopItem = shopItem)
        viewModel.clearItemChosen()
        assertNull(viewModel.navigateWithSelectedItem.value)
    }
}