package com.minhto28.dev.shopping.ui.auth.signup.createAccount

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.minhto28.dev.shopping.R
import com.minhto28.dev.shopping.databinding.FragmentSignUpBinding


class SignUpFragment : Fragment() {

    private var _binding: FragmentSignUpBinding? = null
    private val binding get() = _binding!!
    private val signUpViewModel: SignUpViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSignUpBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val username = arguments?.getString("username")
        binding.edtUsername.setText(username.toString())
        checkFields()
        signin()
    }

    private fun signin() {
        binding.btnSignin.setOnClickListener {
            findNavController().popBackStack(R.id.signInFragment,false)
        }
    }

    private fun checkFields() {
        with(binding){
            edtFullname.checkEmty()
            edtPassword.checkEmty()
            edtRepeatPassword.checkEmty()
        }
    }


    private fun EditText.checkEmty() {
        addTextChangedListener {
            val text = it.toString().trim().isEmpty()
            with(binding) {
                btnSignup.enable()
                when (this@checkEmty) {
                    edtFullname -> {
                        tilFullname.error = if (text) "Vui lòng nhập họ và tên" else null
                    }
                    edtPassword -> {
                        tilPassword.error = if (text) "Vui lòng nhập mật khẩu" else null
                        tilRepeatPassword.error =  if (edtPassword.data()!=edtRepeatPassword.data()) "Mật khẩu không trùng khớp" else null
                    }
                    edtRepeatPassword -> {
                        tilRepeatPassword.error = if (text) "Vui lòng nhập mật khẩu" else  if (edtPassword.data()!=edtRepeatPassword.data()) "Mật khẩu không trùng khớp" else null
                    }
                }
            }
        }
    }

    private fun Button.enable() {
        with(binding) {
            isEnabled = edtFullname.data().isNotEmpty() &&
                    edtUsername.data().isNotEmpty() &&
                    edtPassword.data().isNotEmpty() &&
                    edtRepeatPassword.data().isNotEmpty() &&
                    edtPassword.data() == edtRepeatPassword.data()
        }
    }

    private fun EditText.data() = text.toString().trim()

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}