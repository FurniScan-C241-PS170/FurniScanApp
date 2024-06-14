package com.dicoding.furniscan.ui

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dicoding.furniscan.di.Injection
import com.dicoding.furniscan.repository.ProductRepository
import com.dicoding.furniscan.ui.home.HomeViewModel

class HomeModelFactory(private val repository: ProductRepository) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        when {
            modelClass.isAssignableFrom(HomeViewModel::class.java) -> {
                return HomeViewModel(repository) as T
            }
            else -> throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
        }
    }

    companion object {
        @Volatile
        private var INSTANCE: HomeModelFactory? = null

        fun getInstance(context: Context): HomeModelFactory {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: HomeModelFactory(
                    Injection.provideProductRepository(context)
                ).also { INSTANCE = it }
            }
        }
    }
}