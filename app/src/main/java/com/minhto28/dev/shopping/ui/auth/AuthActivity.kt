package com.minhto28.dev.shopping.ui.auth

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.minhto28.dev.shopping.databinding.ActivityAuthBinding

class AuthActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAuthBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityAuthBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}