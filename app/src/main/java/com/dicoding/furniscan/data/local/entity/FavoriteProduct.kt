package com.dicoding.furniscan.data.local.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize
import java.io.Serializable

@Parcelize
@Entity
data class FavoriteProduct(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int? = 0,

    @ColumnInfo(name = "product_id")
    var productId: Int? = null,

    @ColumnInfo(name = "product_name")
    var productName: String? = null,

    @ColumnInfo(name = "product_price")
    var productPrice: Int? = null,

    @ColumnInfo(name = "product_image")
    var productImage: String? = null,



) : Parcelable, Serializable