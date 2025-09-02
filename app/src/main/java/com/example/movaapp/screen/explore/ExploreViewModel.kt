package com.example.movaapp.screen.explore

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movaapp.di.MovieRepository
import com.example.movaapp.model.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ExploreViewModel @Inject constructor(
    private val repository: MovieRepository
) : ViewModel() {

    private val _movies = MutableStateFlow<List<Result>>(emptyList())
    val movies: StateFlow<List<Result>> = _movies

    init {
        fetchPopularMovies()
    }

     fun fetchPopularMovies() {
        viewModelScope.launch {
            try {
                val apiKey = "0e3c7bf52adb5a1a383b2f8c42024ede"
                // Repository-dən birbaşa List<Movie> alırıq
                val movies = repository.getPopularMovies(apiKey)
                _movies.value = movies
            } catch (e: Exception) {
                // Şəbəkə xətası və ya digər problemlər üçün
                // _movies.value = emptyList() və ya xəta vəziyyəti üçün başqa bir StateFlow istifadə edə bilərsiniz
            }
        }
    }
}