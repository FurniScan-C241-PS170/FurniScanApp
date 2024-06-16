package com.dicoding.furniscan.di

import android.content.Context
import com.dicoding.furniscan.data.preference.TokenPreferences
import com.dicoding.furniscan.data.preference.dataStore
import com.dicoding.furniscan.data.remote.ApiConfig
import com.dicoding.furniscan.repository.AuthRepository
import com.dicoding.furniscan.repository.PredictRepository
import com.dicoding.furniscan.repository.ProductRepository
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking

object Injection {
    fun provideRepository(context: Context): AuthRepository {
        val pref = TokenPreferences.getInstance(context.dataStore)
        val token = runBlocking {
            pref.getToken().first()
        }
        val apiService = ApiConfig.getApiConfig(token.toString())

        return AuthRepository.getInstance(apiService, pref)
    }

    fun provideProductRepository(context: Context): ProductRepository {
        val pref = TokenPreferences.getInstance(context.dataStore)
        val token = runBlocking {
            pref.getToken().first()
        }
        val apiService = ApiConfig.getApiConfig(token.toString())

        return ProductRepository.getInstance(apiService)
    }

    fun providePredictRepository(context: Context): PredictRepository {
        val pref = TokenPreferences.getInstance(context.dataStore)
        val token = runBlocking {
            pref.getToken().first()
        }
        val apiService = ApiConfig.getApiConfig(token.toString())

        return PredictRepository.getInstance(apiService)
    }
}