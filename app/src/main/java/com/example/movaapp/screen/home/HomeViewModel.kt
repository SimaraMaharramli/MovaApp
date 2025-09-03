package com.example.movaapp.screen.home

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movaapp.di.MovieRepository
import com.example.movaapp.model.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: MovieRepository
): ViewModel() {

    val moviespop = MutableLiveData<List<Result>>()
    val moviestop = MutableLiveData<List<Result>>()
    val moviesupcoming = MutableLiveData<List<Result>>()
     val searchResults = MutableLiveData<List<Result>>()

    init {
        fetchPopMovieMovies()
    }

    fun fetchPopMovieMovies() {
        viewModelScope.launch (Dispatchers.IO){
            try {

                val movie = repository.getPopularMovies()
                withContext(Dispatchers.Main){
                    moviespop.value=movie
                    Log.e("Salmmmm", movie.toString())
                }


            } catch (e: Exception) {
                withContext(Dispatchers.Main){
                    Log.e("Salam", e.toString())

                }

            }
        }
    }
    fun fetchTopMovieMovies() {
        viewModelScope.launch (Dispatchers.IO){
            try {

                val movie = repository.getTopMovies()
                withContext(Dispatchers.Main){
                    moviestop.value=movie
                    Log.e("Salmmmm", movie.toString())
                }


            } catch (e: Exception) {
                withContext(Dispatchers.Main){
                    Log.e("Salam", e.toString())

                }

            }
        }
    }
    fun fetchUpComingMovieMovies() {
        viewModelScope.launch (Dispatchers.IO){
            try {

                val movie = repository.getUpComingMovies()
                withContext(Dispatchers.Main){
                    moviesupcoming.value=movie
                    Log.e("Salmmmm", movie.toString())
                }


            } catch (e: Exception) {
                withContext(Dispatchers.Main){
                    Log.e("Salam", e.toString())

                }

            }
        }
    }

    fun searchMovies(query: String) {
        viewModelScope.launch {
            if (query.isNotEmpty()) {
                val results = repository.searchMovies(query)
                searchResults.value = results
            } else {
                searchResults.value = emptyList()
            }
        }
    }
}