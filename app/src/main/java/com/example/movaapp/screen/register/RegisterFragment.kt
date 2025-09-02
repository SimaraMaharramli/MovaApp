package com.example.movaapp.screen.register

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.movaapp.R
import com.example.movaapp.databinding.FragmentLoginBinding
import com.example.movaapp.databinding.FragmentRegisterBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterFragment : Fragment() {
    private lateinit var binding: FragmentRegisterBinding
    private val viewModel by viewModels<RegisterViewModel>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    private fun register(){
        val email = binding.EditTextEmail.text.toString().trim()
        val pass = binding.EditTextPass.text.toString().trim()
        viewModel.registerUser(email,pass)
    }

    private fun observeData(){
        viewModel.isError.observe(viewLifecycleOwner){
            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
        }


        viewModel.isSuccess.observe(viewLifecycleOwner){
            if(it){
                findNavController().navigate(RegisterFragmentDirections.actionRegisterFragmentToLoginPasswordFragment())
            }
        }
    }

    private fun shakeView(view: View) {
        val shake = AnimationUtils.loadAnimation(requireContext(), R.anim.shake)
        view.startAnimation(shake)
    }

    private fun validateInputs(): Boolean {
        var isValid = true



        val email = binding.EditTextEmail.text.toString().trim()
        if (email.isEmpty()) {
            binding.textInputMail.error = "Email boş ola bilməz"
            shakeView(binding.textInputMail)
            isValid = false
        } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            binding.textInputMail.error = "Email formatı düzgün deyil"
            shakeView(binding.textInputMail)
            isValid = false
        } else {
            binding.textInputMail.error = null
        }

        val pass = binding.EditTextPass.text.toString().trim()
        if (pass.isEmpty()) {
            binding.textInputPass.error = "Şifrə boş ola bilməz"
            shakeView(binding.textInputPass)
            isValid = false
        } else if (pass.length < 6) {
            binding.textInputPass.error = "Şifrə ən azı 6 simvol olmalıdır"
            shakeView(binding.textInputPass)
            isValid = false
        } else {
            binding.textInputPass.error = null
        }

        return isValid
       }
}
