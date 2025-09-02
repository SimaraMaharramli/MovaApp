package com.example.movaapp.screen.explore

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.movaapp.R
import com.example.movaapp.databinding.FragmentExploreBinding
import com.example.movaapp.databinding.FragmentHomeBinding
import com.example.movaapp.screen.home.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ExploreFragment : Fragment() {
    private lateinit var binding: FragmentExploreBinding
    private val viewModel by viewModels<ExploreViewModel>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentExploreBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.e("dd",viewModel.fetchPopularMovies().toString())
        binding.rvAllMovies.adapter

    }
}