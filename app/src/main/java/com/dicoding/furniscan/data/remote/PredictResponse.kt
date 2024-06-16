package com.dicoding.furniscan.data.remote

import com.google.gson.annotations.SerializedName

data class PredictResponse(

	@field:SerializedName("data")
	val data: DataPredict,

	@field:SerializedName("message")
	val message: String,

	@field:SerializedName("status")
	val status: Int
)

data class DataPredict(

	@field:SerializedName("color")
	val color: List<String>,

	@field:SerializedName("material")
	val material: String,

	@field:SerializedName("category_id")
	val categoryId: Int,

	@field:SerializedName("product_image")
	val productImage: List<String>,

	@field:SerializedName("price")
	val price: Int,

	@field:SerializedName("fabric")
	val fabric: String,

	@field:SerializedName("product_id")
	val productId: Int,

	@field:SerializedName("rating")
	val rating: Float,

	@field:SerializedName("description")
	val description: String,

	@field:SerializedName("stock_quantity")
	val stockQuantity: Int,

	@field:SerializedName("product_name")
	val productName: String,

	@field:SerializedName("dimension")
	val dimension: String
)
