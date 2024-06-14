package com.dicoding.furniscan.data.local.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Update
import com.dicoding.furniscan.data.local.entity.FavoriteProduct
import androidx.room.Query


@Dao
interface FavoriteDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(favUser: FavoriteProduct)

    @Update
    fun update(favUser: FavoriteProduct)

    @Delete
    fun delete(favUser: FavoriteProduct)

    @Query("SELECT  * from FavoriteProduct")
    fun getAllFromDataProduct(): LiveData<List<FavoriteProduct>>

    @Query("SELECT * FROM FavoriteProduct WHERE product_id = :productId")
    fun getFavoriteProductById(productId: Int): LiveData<FavoriteProduct>


}