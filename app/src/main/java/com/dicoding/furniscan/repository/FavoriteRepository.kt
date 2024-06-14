package com.dicoding.furniscan.repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.dicoding.furniscan.data.local.entity.FavoriteProduct
import com.dicoding.furniscan.data.local.room.FavoriteDao
import com.dicoding.furniscan.data.local.room.FavoriteDatabase
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class FavoriteRepository (application: Application) {

    private val mFavDao: FavoriteDao
    private val executorService: ExecutorService = Executors.newSingleThreadExecutor()

    init {
        val db = FavoriteDatabase.getDatabase(application)
        mFavDao = db.favDao()
    }

    fun getAllData(): LiveData<List<FavoriteProduct>> = mFavDao.getAllFromDataProduct()

    fun insert(favUser: FavoriteProduct) {
        executorService.execute { mFavDao.insert(favUser) }
    }

    fun delete(favUser: FavoriteProduct) {
        executorService.execute { mFavDao.delete(favUser) }
    }

    fun getFavoriteProductById(productId: Int): LiveData<FavoriteProduct> = mFavDao.getFavoriteProductById(productId)


}