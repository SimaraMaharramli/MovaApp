package com.example.movaapp.screen.explore

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.movaapp.R
import com.example.movaapp.adapter.ExploreAdapter
import com.example.movaapp.adapter.SearchAdapter
import com.example.movaapp.databinding.FragmentExploreBinding
import com.example.movaapp.databinding.FragmentHomeBinding
import com.example.movaapp.screen.home.HomeFragmentDirections
import com.example.movaapp.screen.home.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ExploreFragment : Fragment() {
    private lateinit var binding: FragmentExploreBinding
    private val viewModel by viewModels<ExploreViewModel>()
    private val viewModel1 by viewModels<HomeViewModel>()
    val adapter =ExploreAdapter()
    val adapters =SearchAdapter()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentExploreBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeData()
        observeViewModel()
        binding.rvAllMovies.adapter=adapter
        viewModel.fetchExploreMovies()
        adapter.setOnItemClickListener { movie ->

            val action = ExploreFragmentDirections.actionExploreFragmentToDetailFragment(movie.id)
            findNavController().navigate(action)
        }
        setupSearchListener()
        binding.rvTopSearches.adapter=adapters




    }

    private fun setupSearchListener() {
        binding.editTextSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

                val searchText = s.toString().trim()
                viewModel1.searchMovies(searchText)

                // UI'ı hemen güncelle (yükleniyor göstergesi vs.)
                if (searchText.isEmpty()) {
                    binding.rvAllMovies.visibility = View.VISIBLE
                    binding.rvTopSearches.visibility = View.GONE
                } else {
                    binding.rvAllMovies.visibility = View.GONE
                    binding.rvTopSearches.visibility = View.VISIBLE
                }
            }

            override fun afterTextChanged(s: Editable?) {}
        })
    }

    private fun observeViewModel() {
        viewModel1.searchResults.observe(viewLifecycleOwner) { movies ->
            // ViewModel'den gelen sonuçları adapter'a ilet
            adapters.updateList(movies)
        }
    }


    private fun observeData(){
        viewModel.movies.observe(viewLifecycleOwner){
            adapter.updateList(it)
        }

//        viewModel.searchMovies.observe(viewLifecycleOwner){
//            moviesAdapter.updateList(it)
//        }



        }
}