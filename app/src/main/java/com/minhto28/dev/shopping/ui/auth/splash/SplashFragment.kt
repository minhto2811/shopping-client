package com.minhto28.dev.shopping.ui.auth.splash

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.minhto28.dev.shopping.R
import com.minhto28.dev.shopping.databinding.FragmentSplashBinding


class SplashFragment : Fragment() {


    private var _binding: FragmentSplashBinding? = null
    private val binding get() = _binding!!
    private val splashViewModel:SplashViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSplashBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Handler(Looper.getMainLooper()).postDelayed(Runnable {
            findNavController().navigate(R.id.action_splashFragment_to_signInFragment)
        },2000)
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding =null
    }


}