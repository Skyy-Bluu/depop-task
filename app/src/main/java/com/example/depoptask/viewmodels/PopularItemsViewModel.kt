package com.example.depoptask.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.depoptask.network.DepopApiService
import com.example.depoptask.network.ShopItem
import io.reactivex.rxjava3.schedulers.Schedulers

class PopularItemsViewModel(private val depopApiService: DepopApiService) : ViewModel() {

    private val _shopItems = MutableLiveData<MutableList<ShopItem>>()

    val shopItems: LiveData<MutableList<ShopItem>>
        get() = _shopItems

    private val _navigateWithSelectedItem = MutableLiveData<ShopItem>()

    val navigateWithSelectedItem: LiveData<ShopItem>
        get() = _navigateWithSelectedItem

    init {
        getPopularItemDataFromEndpoint()
    }

    private fun getPopularItemDataFromEndpoint() {
        depopApiService.getPopularItems()
            .subscribeOn(Schedulers.io())
            .subscribe { value, error ->
                value?.let {
                    _shopItems.postValue(it.objects.toMutableList())
                }
                error?.let {
                    Log.e(TAG, "Failure: ${it.message}")
                }
            }
    }

    //Coroutines implementation
//    private fun getPopularItemDataFromEndpoint() {
//        viewModelScope.launch {
//            try {
//                depopApiService.getPopularItems().let {
//                    _shopItems.postValue(it.objects.toMutableList())
//                }
//            } catch (e:Exception){
//                Log.e(TAG, "Failure: ${e.message}")
//            }
//        }
//    }

    fun chooseItem(shopItem: ShopItem) {
        _navigateWithSelectedItem.value = shopItem
    }

    fun itemChosen() {
        _navigateWithSelectedItem.value = null
    }

    companion object {
        const val TAG = "PopularItemsViewModel"
    }
}