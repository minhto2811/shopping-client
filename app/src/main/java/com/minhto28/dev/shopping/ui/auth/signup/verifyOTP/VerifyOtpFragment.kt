package com.minhto28.dev.shopping.ui.auth.signup.verifyOTP

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.minhto28.dev.shopping.R
import com.minhto28.dev.shopping.databinding.FragmentVerifyOtpBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class VerifyOtpFragment : Fragment() {


    private var _binding: FragmentVerifyOtpBinding? = null
    private val binding get() = _binding!!

    private val verifyOTViewModel: VerifyOTViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentVerifyOtpBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val username = arguments?.getString("username")
        binding.tvTitleVerifyCode.text =
            "Hãy nhập mã xác nhận được gửi đến $username để tiếp tục tạo tài khoản"
        onTextChange()
        setTimeResendCode(60)
        resendCode(username)
        verifyCode(username)
    }

    private fun verifyCode(username: String?) {
        with(binding) {
            btnVerifyCode.setOnClickListener {
                edtCode.clearFocus()
                binding.tilCode.error = null
                val otp = edtCode.text.toString().trim()
                verifyOTViewModel.verifyOtp(username!!, otp){
                    Toast.makeText(requireContext(), it!!.message, Toast.LENGTH_SHORT).show()
                    when (it?.code) {
                        200 -> {
                            val bundle = bundleOf("username" to username)
                            findNavController().navigate(
                                R.id.action_verifyOtpFragment_to_signUpFragment,
                                bundle
                            )
                        }

                        400, 404 -> {
                            binding.tilCode.error = it.message
                            binding.edtCode.requestFocus(5)
                        }

                        500 -> {
                            Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }
        }
    }

    private fun resendCode(username: String?) {
        binding.tvResendCode.setOnClickListener {
            setTimeResendCode(60)
            verifyOTViewModel.sendCode(username!!)
        }
    }

    private fun setTimeResendCode(seconds: Int) {
        binding.tvResendCode.isEnabled = false
        binding.tvTime.visibility = View.VISIBLE
        var time = seconds
        val scope = CoroutineScope(Dispatchers.Main)
        scope.launch {
            while (time > 0) {
                binding.tvTime.text = time.toString()
                time--
                delay(1000)
            }
            if (time == 0) {
                binding.tvTime.visibility = View.GONE
                binding.tvResendCode.isEnabled = true
                scope.cancel()
            }
        }
    }

    private fun onTextChange() {
        with(binding) {
            edtCode.addTextChangedListener {
                btnVerifyCode.isEnabled = it.toString().trim().length == 6
            }
        }
    }

    override fun onResume() {
        super.onResume()
        Log.e("ghg",binding.btnVerifyCode.isEnabled.toString())
    }


}