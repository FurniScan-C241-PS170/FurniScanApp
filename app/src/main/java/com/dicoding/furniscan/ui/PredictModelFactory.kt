package com.dicoding.furniscan.ui

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dicoding.furniscan.di.Injection
import com.dicoding.furniscan.repository.PredictRepository
import com.dicoding.furniscan.ui.result.ResultViewModel
import com.dicoding.furniscan.ui.scan.ScanViewModel

class PredictModelFactory(private val repository: PredictRepository) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        when {
            modelClass.isAssignableFrom(ScanViewModel::class.java) -> {
                return ScanViewModel(repository) as T
            }

            modelClass.isAssignableFrom(ResultViewModel::class.java) -> {
                return ResultViewModel(repository) as T
            }

            else -> throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
        }
    }

    companion object {
        @Volatile
        private var INSTANCE: PredictModelFactory? = null

        fun getInstance(context: Context): PredictModelFactory {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: PredictModelFactory(
                    Injection.providePredictRepository(context)
                ).also { INSTANCE = it }
            }
        }
    }
}