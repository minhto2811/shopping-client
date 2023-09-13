package com.minhto28.dev.shopping.api

import com.minhto28.dev.shopping.model.Respone
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ApiService {
    @FormUrlEncoded
    @POST("user/receive-otp")
    fun receiveOtp(@Field("username") username: String): Call<Respone?>

    @FormUrlEncoded
    @POST("user/verify-otp")
    fun verifyOtp(@Field("username") username: String, @Field("otp") otp: String): Call<Respone?>


}