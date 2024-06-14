package com.dicoding.furniscan.ui.detail

import android.app.Application
import androidx.lifecycle.ViewModel
import com.dicoding.furniscan.data.local.entity.FavoriteProduct
import com.dicoding.furniscan.repository.FavoriteRepository
import com.dicoding.furniscan.repository.ProductRepository

class DetailViewModel(application: Application, private val repository: ProductRepository) :ViewModel(){
    private val mFavoriteRepository: FavoriteRepository = FavoriteRepository(application)

    fun insert(favUser: FavoriteProduct) {
        mFavoriteRepository.insert(favUser)
    }

    fun delete(favUser: FavoriteProduct) {
        mFavoriteRepository.delete(favUser)
    }

    fun getFavoriteProductById(productId: Int) = mFavoriteRepository.getFavoriteProductById(productId)

    fun getDetailProduct(productId: Int) = repository.getDetailProduct(productId)

}