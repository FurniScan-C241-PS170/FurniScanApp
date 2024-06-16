package com.dicoding.furniscan.data.remote

import okhttp3.MultipartBody
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Path

interface ApiService {
    @FormUrlEncoded
    @POST("users/signup")
    suspend fun signup(
        @Field("username") name: String,
        @Field("email") email: String,
        @Field("password") password: String,
        @Field("repassword") repassword: String
    ): LoginResult

    @FormUrlEncoded
    @POST("users/login")
    suspend fun login(
        @Field("email") email: String,
        @Field("password") password: String
    ): LoginResult


    @GET ("product")
    suspend fun getProduct(
    ): ProductResponse

    @GET("product/{productId}")
    suspend fun getDetailProduct(
        @Path("productId") productId: Int
    ): DetailResponse

    @Multipart
    @POST("product/predict")
    suspend fun predictProduct(
        @Part image: MultipartBody.Part
    ): PredictResponse

    @GET("category")
    suspend fun getCategory(
    ): CategoryResponse



}