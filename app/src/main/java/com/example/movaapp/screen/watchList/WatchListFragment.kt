package com.example.movaapp.screen.watchList

import android.os.Bundle
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
import com.example.movaapp.adapter.WatchListAdapter
import com.example.movaapp.databinding.FragmentExploreBinding
import com.example.movaapp.databinding.FragmentWatchListBinding
import com.example.movaapp.screen.explore.ExploreFragmentDirections
import com.example.movaapp.screen.explore.ExploreViewModel
import com.example.movaapp.screen.home.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class WatchListFragment : Fragment() {
    private lateinit var binding: FragmentWatchListBinding
//    private val viewModel1 by viewModels<HomeViewModel>()
    private val viewModel by viewModels<WatchListViewModel>()
    val adapter = ExploreAdapter()
    val watchadapters = WatchListAdapter()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentWatchListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeData()

        binding.rvAllMovies.adapter=watchadapters

        adapter.setOnItemClickListener { movie ->

            val action = WatchListFragmentDirections.actionWatchListFragmentToDetailFragment(movie.id)
            findNavController().navigate(action)
        }






    }

//    private fun observeData(){
//        viewModel.allTodos.observe(viewLifecycleOwner) { watchList ->
//            if (watchList != null) {
//                watchadapters.updateList(watchList)
//                Log.e("Succec", "Succec to fetch movie details.")
//            } else {
//                Log.e("Fail", "Failed to fetch movie details.")
//            }
//        }
//    }


    private fun observeData() {

        lifecycleScope.launch {
            viewModel.allTodos.collectLatest { todos ->

                watchadapters.updateList(todos)
            }
        }
    }
}