package com.dicoding.furniscan.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.dicoding.furniscan.Result
import com.dicoding.furniscan.data.remote.ApiService
import com.dicoding.furniscan.data.remote.CategoryItem
import com.dicoding.furniscan.data.remote.Detail
import com.dicoding.furniscan.data.remote.ErrorResponse
import com.dicoding.furniscan.data.remote.ProductResponse
import com.google.gson.Gson
import retrofit2.HttpException

class ProductRepository(
    private var apiService: ApiService
    ) {
    fun getProduct(): LiveData<Result<ProductResponse>> =
        liveData {
            emit(Result.Loading)
            try {
                val response = apiService.getProduct()
                emit(Result.Success(response))
            } catch (e: HttpException) {
                val error =
                    Gson().fromJson(e.response()?.errorBody()?.string(), ErrorResponse::class.java)
                emit(Result.Error(error.message.toString()))
            } catch (e: Exception) {
                emit(Result.Error(e.message.toString()))
            }
        }

    fun getDetailProduct(productId: Int): LiveData<Result<Detail>> =
        liveData {
            emit(Result.Loading)
            try {
                val response = apiService.getDetailProduct(productId)
                emit(Result.Success(response.data[0]))
            } catch (e: HttpException) {
                val error =
                    Gson().fromJson(e.response()?.errorBody()?.string(), ErrorResponse::class.java)
                emit(Result.Error(error.message.toString()))
            } catch (e: Exception) {
                emit(Result.Error(e.message.toString()))
            }
        }

    fun getCategory(): LiveData<Result<CategoryItem>> =
        liveData {
            emit(Result.Loading)
            try {
                val response = apiService.getCategory()
                emit(Result.Success(response.data))
            } catch (e: HttpException) {
                val error =
                    Gson().fromJson(e.response()?.errorBody()?.string(), ErrorResponse::class.java)
                emit(Result.Error(error.message.toString()))
            } catch (e: Exception) {
                emit(Result.Error(e.message.toString()))
            }
        }



    companion object {
        @Volatile
        private var instance: ProductRepository? = null

        fun getInstance(
            apiService: ApiService
        ): ProductRepository =
            instance ?: synchronized(this) {
                instance ?: ProductRepository(apiService).also { instance = it }
            }
    }
}