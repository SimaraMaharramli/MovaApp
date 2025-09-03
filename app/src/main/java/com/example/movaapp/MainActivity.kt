package com.example.movaapp

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.movaapp.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        val navController = navHostFragment.navController

        navController.addOnDestinationChangedListener{_, destination,_ ->
            when(destination.id){
                R.id.homeFragment ->  binding.bottomNavigationView.visibility = View.VISIBLE
                R.id.watchListFragment ->  binding.bottomNavigationView.visibility = View.VISIBLE
                R.id.profilFragment2 ->  binding.bottomNavigationView.visibility = View.VISIBLE
                else ->   binding.bottomNavigationView.visibility = View.GONE

            }

        }

        val navHostFragment1 = supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        val navControllerhost = navHostFragment1.navController
        binding.bottomNavigationView.setupWithNavController(navControllerhost)
    }
}