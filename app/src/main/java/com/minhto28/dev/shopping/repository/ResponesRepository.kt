package com.minhto28.dev.shopping.repository

import com.minhto28.dev.shopping.api.ApiClient
import com.minhto28.dev.shopping.model.Respone
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONObject

class ResponesRepository {
    val apiService by lazy {
        ApiClient.apiService
    }

    suspend fun getRespone(username:String):Respone? {
        return withContext(Dispatchers.IO) {
            val response = apiService.receiveOtp(username).execute().body()
            response
        }
    }

    suspend fun verifyOtp(username:String,otp:String):Respone? {
        return withContext(Dispatchers.IO) {
            val response = apiService.verifyOtp(username,otp).execute().body()
            response
        }
    }
}