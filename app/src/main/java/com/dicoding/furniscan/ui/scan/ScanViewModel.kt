package com.dicoding.furniscan.ui.scan

import androidx.lifecycle.ViewModel
import com.dicoding.furniscan.repository.PredictRepository
import okhttp3.MultipartBody

class ScanViewModel (private val predictRepository: PredictRepository): ViewModel(){
    fun getPredictResult(image: MultipartBody.Part) = predictRepository.predictProduct(image)
}