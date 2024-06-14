package com.dicoding.furniscan.ui

import android.app.Application
import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dicoding.furniscan.di.Injection
import com.dicoding.furniscan.repository.ProductRepository
import com.dicoding.furniscan.ui.detail.DetailViewModel
import com.dicoding.furniscan.ui.favorite.FavoriteViewModel

class FavoriteModelFactory(
    private val application: Application, private val productRepository: ProductRepository
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        when {
            modelClass.isAssignableFrom(DetailViewModel::class.java) -> {
                return DetailViewModel(application, productRepository) as T
            }

            modelClass.isAssignableFrom(FavoriteViewModel::class.java) -> {
                return FavoriteViewModel(application, productRepository) as T
            }

            else -> throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
        }
    }

    companion object {
        @Volatile
        private var INSTANCE: FavoriteModelFactory? = null

        fun getInstance(application: Application): FavoriteModelFactory {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: FavoriteModelFactory(application, Injection.provideProductRepository(application)
                ).also { INSTANCE = it }
            }
        }
    }

}