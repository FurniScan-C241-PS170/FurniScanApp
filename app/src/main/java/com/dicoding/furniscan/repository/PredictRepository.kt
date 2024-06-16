package com.dicoding.furniscan.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.dicoding.furniscan.Result
import com.dicoding.furniscan.data.remote.ApiService
import com.dicoding.furniscan.data.remote.DataProduct
import com.dicoding.furniscan.data.remote.ErrorResponse
import com.dicoding.furniscan.data.remote.PredictResponse
import com.dicoding.furniscan.data.remote.ProductResponse
import com.google.gson.Gson
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import retrofit2.HttpException
import java.io.File

class PredictRepository(
    private var apiService: ApiService
) {
    fun predictProduct(image: MultipartBody.Part): LiveData<Result<PredictResponse>> =
        liveData {
            emit(Result.Loading)
            try {
                val response = apiService.predictProduct(image)
                emit(Result.Success(response))
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
        private var instance: PredictRepository? = null

        fun getInstance(
            apiService: ApiService
        ): PredictRepository =
            instance ?: synchronized(this) {
                instance ?: PredictRepository(apiService).also { instance = it }
            }
    }
}