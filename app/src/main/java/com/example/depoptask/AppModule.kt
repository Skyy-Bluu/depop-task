package com.example.depoptask

import com.example.depoptask.viewmodels.PopularItemsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object AppModule {
    val appModule = module {

        // MyViewModel ViewModel
        viewModel { PopularItemsViewModel() }
    }
}