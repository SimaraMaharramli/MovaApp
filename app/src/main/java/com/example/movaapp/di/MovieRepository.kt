package com.example.movaapp.di

import android.graphics.Movie
import com.example.movaapp.api.MovieApiService
import com.example.movaapp.model.Result
import javax.inject.Inject

class MovieRepository @Inject constructor(
    private val apiService: MovieApiService
) {
    suspend fun getPopularMovies(apiKey: String): List<Result> {
        val response = apiService.getPopularMovies(apiKey)
        return if (response.isSuccessful) {
            response.body()?.results ?: emptyList()
        } else {
            // Xəta olarsa boş siyahı qaytarırıq
            emptyList()
        }
    }
}