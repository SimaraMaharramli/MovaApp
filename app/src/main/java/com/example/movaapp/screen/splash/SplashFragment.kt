package com.example.movaapp.screen.splash

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.movaapp.databinding.FragmentSplashBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashFragment : Fragment() {
    private lateinit var binding: FragmentSplashBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSplashBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//
//        Handler(Looper.getMainLooper()).postDelayed({
//            checkOnboardingStatus()
//        }, 2000)

        checkFlow()
    }
    private fun checkFlow(){

        val sp = requireContext().getSharedPreferences("local_shared", Context.MODE_PRIVATE)
        val isOnboardingCompleted = sp.getBoolean("isCompleted", false)
        val isLogin = sp.getBoolean("isLogin", false)

        lifecycleScope.launch {
            delay(5000)

            if(!isOnboardingCompleted){
                findNavController().navigate(SplashFragmentDirections.actionSplashFragment2ToOnBoardingFragment())
            } else if(isLogin){
                Toast.makeText(context, "Home", Toast.LENGTH_SHORT).show()
            } else {
                findNavController().navigate(SplashFragmentDirections.actionSplashFragment2ToHomeFragment())
            }
}



        }



//    private fun checkOnboardingStatus() {
//        val isFirstTime = SharedPreferencesManager.isFirstTime(requireContext())
//        if (isFirstTime) {
//            findNavController().navigate(SplashFragmentDirections.actionSplashFragment2ToOnBoardingFragment())
//        } else {
//            findNavController().navigate(SplashFragmentDirections.actionSplashFragment2ToHomeFragment())
//        }
//    }
}