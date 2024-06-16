package com.dicoding.furniscan.ui.category

import androidx.lifecycle.ViewModel
import com.dicoding.furniscan.repository.ProductRepository

class CategoryViewModel (private val productRepository: ProductRepository): ViewModel(){
    fun getCategories() = productRepository.getCategory()
}