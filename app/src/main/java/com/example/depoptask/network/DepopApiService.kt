package com.example.depoptask.network

import android.util.Log
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "https://api.garage.me/api/v1/products/popular/"

private val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
    .baseUrl(BASE_URL)
    .build()

interface PokemonApiService {
    @GET("?offset_id=")
    fun getPopularItems(): Deferred<ShopItems>

}

object PokemonApi {
    val retrofitService: PokemonApiService by lazy {
        retrofit.create(PokemonApiService::class.java)
    }
}

suspend fun getPopularItems()
        : ShopItems? = withContext(Dispatchers.IO){
    val getPokemonDeferred = PokemonApi.retrofitService.getPopularItems()
    try {
        getPokemonDeferred.await()
    } catch (e: Exception) {
        Log.e("GameViewModel", e.toString())
        null
    }
}