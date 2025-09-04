package com.example.movaapp.screen.detail

import android.app.AlertDialog
import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.movaapp.adapter.CastAdapter
import com.example.movaapp.adapter.TopMoviesAdapter
import com.example.movaapp.adapter.TrailerAdapter
import com.example.movaapp.databinding.FragmentDetailBinding
import com.example.movaapp.model.WatchListModel
import com.example.movaapp.screen.watchList.WatchListViewModel
import com.example.movies.adapter.CommentAdapter
import com.google.android.material.tabs.TabLayout
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : Fragment() {
    private lateinit var binding: FragmentDetailBinding
    private val viewModel by viewModels<DetailViewModel>()
    private val trailerAdapter = TrailerAdapter()
    private val commentAdapter = CommentAdapter()
    private val similrAdapter = TopMoviesAdapter()
    private val viewModelwatch by viewModels<WatchListViewModel>()
    private val args: DetailFragmentArgs by navArgs()
    val adapter=CastAdapter()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.imageView11.setOnClickListener {

            findNavController().popBackStack()
        }
        val movieId = args.movieId
        Log.e("DetailFragment", "Received movie ID: $movieId")

       val detaildata= viewModel.fetchDetailMovies(movieId)
        viewModel.fetchCreditsMovies(movieId)
        viewModelwatch.getbyid(movieId)
        viewModel.getsimilr(movieId)
        viewModel.getVideos(movieId)
        viewModel.getComments(movieId)
        observeData()
        binding.rvCasts.adapter=adapter
        binding.rvTrailer.adapter = trailerAdapter
        binding.rvSimilar.adapter=similrAdapter
        binding.rvComment.adapter=commentAdapter

        binding.rvTrailer.visibility=View.VISIBLE
        binding.rvSimilar.visibility=View.GONE
        binding.rvComment.visibility=View.GONE

        trailerAdapter.onItemClick = { video ->
            openYoutube(requireContext(), video.key.toString())
            }
//        binding.imageView14.setOnClickListener(){
//            viewModelwatch.addMovieToWatchlist(14058804,movieId)
//        }

        binding.imageView14.setOnClickListener {
            val movieDetails = viewModel.movies.value
            val id=viewModelwatch.id.value

            if (movieDetails != null) {
                if (id!=null) {
                    Toast.makeText(context, "already added to watchlist!", Toast.LENGTH_SHORT).show()
                }else {
                    val watchListMovie = WatchListModel(
                        title = movieDetails.original_title,
                        image = movieDetails.poster_path ?: "",
                        moveid = movieDetails.id
                    )
                    viewModelwatch.addList(watchListMovie)
                    Toast.makeText(context, "Added to watchlist!", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(context, "Movie details not available", Toast.LENGTH_SHORT).show()
            }
        }


        binding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                if (tab.position == 0) {
                    binding.rvTrailer.visibility = View.VISIBLE
                    binding.rvSimilar.visibility = View.GONE
                    binding.rvComment.visibility = View.GONE
                    trailerAdapter.onItemClick = { video ->
                        openYoutube(requireContext(), video.key.toString())
                    }

                }

                if (tab.position == 1) {
                    binding.rvTrailer.visibility = View.GONE
                    binding.rvSimilar.visibility = View.VISIBLE
                    binding.rvComment.visibility = View.GONE




                }

                if (tab.position == 2) {
                    binding.rvTrailer.visibility = View.GONE
                    binding.rvSimilar.visibility = View.GONE
                    binding.rvComment.visibility = View.VISIBLE
                }



            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {}
            override fun onTabReselected(tab: TabLayout.Tab?){}
            })

    }


    private fun openYoutube(context: Context, youtubeKey: String) {
        val youtubeAppIntent = Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:$youtubeKey"))
        val youtubeWebIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=$youtubeKey"))

        try {
            context.startActivity(youtubeAppIntent)
        } catch (e: ActivityNotFoundException) {
            context.startActivity(youtubeWebIntent)
           }
        }







    private fun observeData(){
        viewModel.movies.observe(viewLifecycleOwner) { movieDetails ->
            if (movieDetails != null) {
                binding.movie = movieDetails

                Log.e("DetailFragment", "Movie details fetched: ${movieDetails.title}")

            } else {
                Log.e("DetailFragment", "Failed to fetch movie details.")
            }
        }

        viewModel.videos.observe(viewLifecycleOwner){
            trailerAdapter.updateList(it)
           }
        viewModel.comments.observe(viewLifecycleOwner){
            commentAdapter.updateList(it)
           }
        viewModel.similr.observe(viewLifecycleOwner){
            similrAdapter.updateList(it)
           }

        viewModelwatch.id.observe(viewLifecycleOwner) { movieDetails ->

        }
        viewModel.moviescast.observe(viewLifecycleOwner) { casts ->
            if (casts.isNotEmpty()) {
//                adapter.updateList(casts)
                val nonNullCasts = casts.filterNotNull()
                adapter.updateList(nonNullCasts)
                Log.e("DetailFragment", "Movie cast fetched: ${casts.size} members")
            } else {
                Log.e("DetailFragment", "Failed to fetch movie cast.")
            }
        }

    }

//    private fun observeDataclick() {
//        viewModel.movies.observe(viewLifecycleOwner) { movieDetails ->
//            if (movieDetails != null) {
//                binding.movie = movieDetails
//
//                Log.e("DetailFragment", "Movie details fetched: ${movieDetails.title}")
//
//            } else {
//                Log.e("DetailFragment", "Failed to fetch movie details.")
//            }
//        }
//    }

    }


