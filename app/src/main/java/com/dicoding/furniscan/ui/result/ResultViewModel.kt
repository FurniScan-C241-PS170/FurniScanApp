package com.dicoding.furniscan.ui.result

import androidx.lifecycle.ViewModel
import com.dicoding.furniscan.repository.PredictRepository
import okhttp3.MultipartBody

class ResultViewModel (private val predictRepository: PredictRepository): ViewModel(){
    fun getPredictResult(image: MultipartBody.Part) = predictRepository.predictProduct(image)

}