package com.dicoding.furniscan.data.remote

import android.os.Parcelable
import androidx.room.Entity
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import java.io.Serializable

data class ProductResponse(

	@field:SerializedName("data")
	val data: List<DataProduct>,

	@field:SerializedName("message")
	val message: String,

	@field:SerializedName("status")
	val status: Int
)

@Parcelize
data class DataProduct(

	@field:SerializedName("color")
	val color: List<String>? = null,

	@field:SerializedName("material")
	val material: String? = null,

	@field:SerializedName("category_id")
	val categoryId: Int? = null,

	@field:SerializedName("product_image")
	val productImage: List<String>? = null,

	@field:SerializedName("price")
	val price: Int? = null,

	@field:SerializedName("fabric")
	val fabric: String? = null,

	@field:SerializedName("product_id")
	val productId: Int = 0,

	@field:SerializedName("rating")
	val rating: Float? = null,

	@field:SerializedName("description")
	val description: String? = null,

	@field:SerializedName("stock_quantity")
	val stockQuantity: Int? = null,

	@field:SerializedName("product_name")
	val productName: String? = null,

	@field:SerializedName("dimension")
	val dimension: String? = null
) : Parcelable, Serializable
