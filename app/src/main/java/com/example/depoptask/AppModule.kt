package com.example.depoptask

import com.example.depoptask.network.DepopApiService
import com.example.depoptask.viewmodels.PopularItemsViewModel
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

object AppModule {
    private const val BASE_URL = "https://api.garage.me/api/v1/products/popular/"
    val appModule = module {

        single<Job> {
            Job()
        }

        single {
            CoroutineScope(get<Job>() + Dispatchers.Main)
        }

        single<Moshi> {
            Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
        }

        single<Retrofit> {
            val rxJavaAdapterFactory = RxJava3CallAdapterFactory.createSynchronous()

            Retrofit.Builder()
                .addConverterFactory(MoshiConverterFactory.create(get()))
//                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .addCallAdapterFactory(rxJavaAdapterFactory)
                .baseUrl(BASE_URL)
                .build()
        }
        single<DepopApiService> {
            get<Retrofit>().create(DepopApiService::class.java)
        }

        viewModel { PopularItemsViewModel(get()) }
    }
}