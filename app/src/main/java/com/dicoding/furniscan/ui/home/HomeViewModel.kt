package com.dicoding.furniscan.ui.home

import androidx.lifecycle.ViewModel
import com.dicoding.furniscan.repository.ProductRepository

class HomeViewModel (private val productRepository: ProductRepository): ViewModel(){
    fun getProduct() = productRepository.getProduct()

}