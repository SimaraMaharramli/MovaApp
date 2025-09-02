package com.example.movaapp.screen.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.movaapp.adapter.TopMoviesAdapter
import com.example.movaapp.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private val viewModel by viewModels<HomeViewModel>()
    private val topMoviesAdapter = TopMoviesAdapter()
    private val popularMoviesAdapter = TopMoviesAdapter()
    private val upcomingMoviesAdapter = TopMoviesAdapter()






    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rvTopMovies.adapter = topMoviesAdapter
        binding.rvPopular.adapter = popularMoviesAdapter
        binding.rvUpcoming.adapter = upcomingMoviesAdapter

    }
}