package com.dicoding.furniscan.data.remote

import com.google.gson.annotations.SerializedName

data class CategoryResponse(

	@field:SerializedName("data")
	val data: CategoryItem,

	@field:SerializedName("message")
	val message: String,

	@field:SerializedName("status")
	val status: Int
)

data class CategoryItem(

	@field:SerializedName("category_name")
	val categoryName: String,

	@field:SerializedName("category_id")
	val categoryId: Int
)
