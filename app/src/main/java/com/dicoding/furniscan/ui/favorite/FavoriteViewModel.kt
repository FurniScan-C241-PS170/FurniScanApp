package com.dicoding.furniscan.ui.favorite

import android.app.Application
import androidx.lifecycle.ViewModel
import com.dicoding.furniscan.data.local.entity.FavoriteProduct
import com.dicoding.furniscan.repository.FavoriteRepository
import com.dicoding.furniscan.repository.ProductRepository

class FavoriteViewModel(application: Application, private val productRepository: ProductRepository) : ViewModel(){
    private val mFavoriteRepository: FavoriteRepository = FavoriteRepository(application)

    fun insert(favUser: FavoriteProduct) {
        mFavoriteRepository.insert(favUser)
    }

    fun delete(favUser: FavoriteProduct) {
        mFavoriteRepository.delete(favUser)
    }

    fun getAllData() = mFavoriteRepository.getAllData()

    fun getAllDataProductById(productId: Int) = mFavoriteRepository.getFavoriteProductById(productId)


}