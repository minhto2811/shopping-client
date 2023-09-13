package com.minhto28.dev.shopping.ui.auth.signup.sendOTP

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.JsonObject
import com.minhto28.dev.shopping.model.Respone
import com.minhto28.dev.shopping.repository.ResponesRepository
import kotlinx.coroutines.launch

class SendOTPViewModel : ViewModel() {
    private val TAG = "SendOTPViewModel"
    private val _respone = MutableLiveData<Respone>()
    val respone: LiveData<Respone> = _respone

    private val responesRepository by lazy {
        ResponesRepository()
    }

    fun sendCode(username: String) {
        viewModelScope.launch {
            try {
                val result = responesRepository.getRespone(username)
                _respone.postValue(result!!)
            } catch (e: Exception) {
                Log.e(TAG, e.message.toString())
            }
        }
    }
}