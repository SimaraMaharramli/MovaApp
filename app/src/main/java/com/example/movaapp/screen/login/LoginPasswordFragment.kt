package com.example.movaapp.screen.login

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.movaapp.R
import com.example.movaapp.databinding.FragmentLoginBinding
import com.example.movaapp.databinding.FragmentLoginPasswordBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginPasswordFragment : Fragment() {
    lateinit var binding: FragmentLoginPasswordBinding
    private val viewModel by viewModels<LoginPasswordViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginPasswordBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeData()

        binding.buttonIn.setOnClickListener {
            if(validate()){
                login()
            }
        }


        binding.textViewUp.setOnClickListener {
            findNavController().navigate(LoginPasswordFragmentDirections.actionLoginPasswordFragmentToRegisterFragment())
        }

        binding.imageViewBacktoLogin.setOnClickListener {
            findNavController().popBackStack()
        }
    }





    private fun login(){

        val email = binding.EditTextEmail.text.toString().trim()
        val password = binding.EditTextPass.text.toString().trim()
        viewModel.loginUser(email,password)

    }

    private fun setUserLogin(){
        val sp = requireContext().getSharedPreferences("local_shared", Context.MODE_PRIVATE)

        sp.edit().putBoolean("isLogin", true).apply()
    }

    private fun observeData(){
        viewModel.isError.observe(viewLifecycleOwner){
            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
        }

        viewModel.isSuccess.observe(viewLifecycleOwner){
            if(it){
                setUserLogin()
                findNavController().navigate(LoginPasswordFragmentDirections.actionLoginPasswordFragmentToHomeFragment())
            }
        }
    }

    private fun shakeView(view: View) {
        val shake = android.view.animation.AnimationUtils.loadAnimation(requireContext(), R.anim.shake)
        view.startAnimation(shake)
    }


    private fun validate(): Boolean{
        var isValid = true
        val email = binding.EditTextEmail.text.toString().trim()
        if(email.isEmpty()){
            binding.textInputLayoutEmail.error = "Email boş ola bilməz"
            shakeView(binding.textInputLayoutEmail)
            isValid = false
        } else if(!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            binding.textInputLayoutEmail.error = "Email formatı düzgün deyil"
            shakeView(binding.textInputLayoutEmail)
            isValid = false
        } else {
            binding.textInputLayoutEmail.error = null
        }

        val pass = binding.EditTextPass.text.toString().trim()
        if (pass.isEmpty()) {
            binding.textInputLayoutPassword.error = "Şifrə boş ola bilməz"
            shakeView(binding.textInputLayoutPassword)
            isValid = false
        } else if (pass.length < 6) {
            binding.textInputLayoutPassword.error = "Şifrə ən azı 6 simvol olmalıdır"
            shakeView(binding.textInputLayoutPassword)
            isValid = false
        } else {
            binding.textInputLayoutPassword.error = null
        }

        return isValid
       }



}

