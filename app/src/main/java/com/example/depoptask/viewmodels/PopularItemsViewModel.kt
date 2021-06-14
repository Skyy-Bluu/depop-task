package com.example.depoptask.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.depoptask.network.ShopItem
import com.example.depoptask.network.getPopularItems
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch


class PopularItemsViewModel : ViewModel() {
    private var viewModelJob = Job()

    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    private val _shopItems = MutableLiveData<MutableList<ShopItem>>()

    val shopItems: LiveData<MutableList<ShopItem>>
        get() = _shopItems

    private val _navigateWithSelectedItem = MutableLiveData<ShopItem>()

    val navigateWithSelectedItem: LiveData<ShopItem>
        get() = _navigateWithSelectedItem

    init {
        coroutineScope.launch {
            getPopularItems()
                ?.let {
                    _shopItems.postValue(it.objects.toMutableList())
                }
        }
    }

    fun chooseItem(shopItem: ShopItem) {
        _navigateWithSelectedItem.value = shopItem
    }

    fun itemChosen() {
        _navigateWithSelectedItem.value = null
    }
}