package com.minhto28.dev.shopping.ui.auth.signup.verifyOTP

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.minhto28.dev.shopping.model.Respone
import com.minhto28.dev.shopping.repository.ResponesRepository
import kotlinx.coroutines.launch

class VerifyOTViewModel : ViewModel() {
    private val TAG = "VerifyOTViewModel"

    private val responesRepository by lazy {
        ResponesRepository()
    }


    fun sendCode(username: String) {
        viewModelScope.launch {
            try {
                responesRepository.getRespone(username)
            } catch (e: Exception) {
                Log.e(TAG, e.message.toString())
            }
        }
    }
    fun verifyOtp(username: String,otp:String,callback:(Respone?)->Unit) {
        viewModelScope.launch {
            try {
                val result = responesRepository.verifyOtp(username,otp)
                callback.invoke(result)
            } catch (e: Exception) {
                Log.e(TAG, e.toString())
            }
        }
    }
}