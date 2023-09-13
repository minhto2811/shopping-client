package com.minhto28.dev.shopping.ui.auth.signup.sendOTP

import android.os.Bundle
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
import com.minhto28.dev.shopping.databinding.FragmentSendOtpBinding

class SendOtpFragment : Fragment() {

    private var _binding: FragmentSendOtpBinding? = null
    private val binding get() = _binding!!
    private val sendOTPViewModel: SendOTPViewModel by viewModels()

    private val emailRegex = "^[\\w-.]+@([\\w-]+\\.)+[\\w-]{2,4}\$".toRegex()

    // regex "(0\\/91)?[7-9][0-9]{9}"
    private val numberPhoneRegex = "(84|0[3|5|7|8|9])+([0-9]{8})\\b".toRegex()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSendOtpBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onTextChange()
        sendOtp()
        sendOTPViewModel.respone.observe(viewLifecycleOwner) {
            when (it.code) {
                200 -> {
                    val username = binding.edtUsername.text.toString().trim()
                    val bundle = bundleOf("username" to username)
                    findNavController().navigate(R.id.action_sendOtpFragment_to_verifyOtpFragment, bundle)
                }
                409->{
                    val direction = binding.edtUsername.text.toString().trim().length-1
                    binding.edtUsername.requestFocus(direction)
                    binding.tilUsername.error = it.message
                }
                500->{
                    Toast.makeText(requireContext(), "${it.message}", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun sendOtp() {
        with(binding) {
            btnSendCode.setOnClickListener {
                edtUsername.clearFocus()
                val username = edtUsername.text.toString().trim()
                sendOTPViewModel.sendCode(username)
            }
        }
    }

    private fun onTextChange() {
        with(binding) {
            edtUsername.addTextChangedListener {
                val text = it.toString().trim()
                val isNumberPhone = numberPhoneRegex.matches(text)
                val isEmail = emailRegex.matches(text)
                btnSendCode.isEnabled = isNumberPhone || isEmail
            }
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}