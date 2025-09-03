package com.example.movaapp.screen.allmovie

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.movaapp.R
import com.example.movaapp.adapter.ExploreAdapter
import com.example.movaapp.databinding.FragmentAllMovieBinding
import com.example.movaapp.databinding.FragmentExploreBinding
import com.example.movaapp.screen.detail.DetailFragmentArgs
import com.example.movaapp.screen.explore.ExploreFragmentDirections
import com.example.movaapp.screen.explore.ExploreViewModel
import com.example.movaapp.screen.home.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AllMovieFragment : Fragment() {
    private lateinit var binding: FragmentAllMovieBinding
    private val viewModel by viewModels<HomeViewModel>()
    private val args: AllMovieFragmentArgs by navArgs()
    val adapter = ExploreAdapter()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAllMovieBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeData()
        binding.rvAllMovies.adapter=adapter
        binding.imageView2.setOnClickListener(){
            findNavController().navigate(AllMovieFragmentDirections.actionAllMovieFragmentToHomeFragment())

        }

        adapter.setOnItemClickListener { movie ->

            val action = AllMovieFragmentDirections.actionAllMovieFragmentToDetailFragment(movie.id)
            findNavController().navigate(action)
        }
        if (args.title=="top"){
            observeData()
            viewModel.fetchTopMovieMovies()
        }
        if (args.title=="pop"){
            observepop()
            binding.textView18.text="Popular"
            viewModel.fetchPopMovieMovies()
        }
        if (args.title=="up"){
            observeup()
            binding.textView18.text="Upcoming"
            viewModel.fetchUpComingMovieMovies()
        }


    }

    private fun observeData(){
        viewModel.moviestop.observe(viewLifecycleOwner){
            adapter.updateList(it)
        }
    }
    private fun observepop(){

        viewModel.moviespop.observe(viewLifecycleOwner){
            adapter.updateList(it)
        }

    }
    private fun observeup(){

        viewModel.moviesupcoming.observe(viewLifecycleOwner){
            adapter.updateList(it)
        }
    }
}