package com.example.movaapp.di

import com.example.movaapp.api.MovieApiService
import com.example.movaapp.model.Cast
import com.example.movaapp.model.CastingModel
import com.example.movaapp.model.MovieCreditsResponse
import com.example.movaapp.model.MovieDetailsResponse
import com.example.movaapp.model.MovieResponse
import com.example.movaapp.model.Result
import com.example.movaapp.model.ResultX
import com.example.movaapp.model.ResultXX
import com.example.movaapp.model.StatusResponse
import com.example.movaapp.model.TrailerResponse
import com.example.movaapp.model.WatchlistRequestBody
import okhttp3.Response
import javax.inject.Inject

class MovieRepository @Inject constructor(
    private val apiService: MovieApiService
) {
    suspend fun getPopularMovies(): List<Result> {
        val response = apiService.getPopularMovies()
        return if (response.isSuccessful) {
            response.body()?.results ?: emptyList()
        } else {

            emptyList()
        }
    }
    suspend fun getTopMovies(): List<Result> {
        val response = apiService.getPopularMovies()
        return if (response.isSuccessful) {
            response.body()?.results ?: emptyList()
        } else {

            emptyList()
        }
    }
    suspend fun getUpComingMovies(): List<Result> {
        val response = apiService.getPopularMovies()
        return if (response.isSuccessful) {
            response.body()?.results ?: emptyList()
        } else {

            emptyList()
        }
    }

    suspend fun getDiscoverMovies(): List<Result> {
        val response = apiService.getExploreMovies()
        return if (response.isSuccessful) {
            response.body()?.results ?: emptyList()
        } else {

            emptyList()
        }
    }


    suspend fun getMovieDetails(movieId: Int): MovieDetailsResponse? {
        val response = apiService.getMovieDetails(movieId)
        return if (response.isSuccessful) {
            response.body()
        } else {
            null
        }
    }
    suspend fun getWatchListMovieDetails(): List<Result>? {
        val response = apiService.getWatchListMovie(22276590)
        return if (response.isSuccessful) {
            response.body()?.results ?: emptyList()
        } else {
            emptyList()
        }
    }

    suspend fun getMovieCredits(movieId: Int): List<Cast> {
        val response = apiService.getMovieCredits(movieId)
        return if (response.isSuccessful) {
            response.body()?.cast?: emptyList()
        } else emptyList()
    }

    suspend fun searchMovies(query: String): List<Result> {
        val response = apiService.searchMovies(query)
        return if (response.isSuccessful) {
            response.body()?.results ?: emptyList()
        } else {
            emptyList()
        }
    }

    suspend fun addToWatchlist(accountId: Int, mediaId: Int): StatusResponse? {
        val requestBody = WatchlistRequestBody(
            mediaType = "movie",
            mediaId = mediaId,
            favorite = true
        )
        val response = apiService.addToWatchlist(
            accountId = accountId,
            watchlist = requestBody
        )
        return if (response.isSuccessful) {
            response.body()
        } else {

            null
        }
    }

//    suspend fun getVideos(movieId: Int): Response<TrailerResponse>{
//        return apiService.getVideos(movieId)
//        }

    suspend fun getVideos(movieId: Int): List<ResultX> {
        val response = apiService.getVideos(movieId)
        return if (response.isSuccessful) {
            response.body()?.results?: emptyList()
        } else emptyList()
    }

    suspend fun getsimilr(movieId: Int): List<Result> {
        val response = apiService.getSimilar(movieId)
        return if (response.isSuccessful) {
            response.body()?.results?: emptyList()
        } else emptyList()
    }
    suspend fun getComments(movieId: Int): List<ResultXX> {
        val response = apiService.getComments(movieId)
        return if (response.isSuccessful) {
            response.body()?.results?: emptyList()
        } else emptyList()
    }

}