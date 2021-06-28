package com.example.depoptask.network

import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET

interface DepopApiService {
    // Coroutines implementation
//    @GET("?offset_id=")
//    suspend fun getPopularItems(): Deferred<ShopItems>
    @GET("?offset_id=")
    fun getPopularItems(): Single<ShopItems>

}