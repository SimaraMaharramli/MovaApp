package com.example.movaapp.api

import com.example.movaapp.model.CommentResponse
import com.example.movaapp.model.MovieCreditsResponse
import com.example.movaapp.model.MovieDetailsResponse
import com.example.movaapp.model.MovieResponse
import com.example.movaapp.model.StatusResponse
import com.example.movaapp.model.TrailerResponse
import com.example.movaapp.model.WatchlistRequestBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApiService {

    @GET("movie/popular")
    suspend fun getPopularMovies(): Response<MovieResponse>
    @GET("movie/top_rated")
    suspend fun getTopMovies(): Response<MovieResponse>
    @GET("movie/upcoming")
    suspend fun geUpcomingMovies(): Response<MovieResponse>


    @GET("discover/movie")
    suspend fun getExploreMovies(): Response<MovieResponse>

    @GET("movie/{movie_id}")
    suspend fun getMovieDetails(@Path("movie_id") movieId: Int): Response<MovieDetailsResponse>
    @GET("/account/{account_id}/watchlist/movies")
    suspend fun getWatchListMovie(@Path("account_id") movieId: Int): Response<MovieResponse>


    @GET("movie/{movie_id}/credits")
    suspend fun getMovieCredits(@Path("movie_id") movieId: Int): Response<MovieCreditsResponse>
    @GET("search/movie")
    suspend fun searchMovies(
        @Query("query") query: String
    ): Response<MovieResponse>


    @POST("account/{account_id}/favorite")
    suspend fun addToWatchlist(
        @Path("account_id") accountId: Int,
        @Body watchlist: WatchlistRequestBody
    ): Response<StatusResponse>

    @GET("movie/{movie_id}/videos")
    suspend fun getVideos(@Path("movie_id") movieId: Int): Response<TrailerResponse>


    @GET("movie/{movie_id}/similar")
    suspend fun getSimilar(@Path("movie_id") movieId: Int): Response<MovieResponse>

    @GET("movie/{movie_id}/reviews")
    suspend fun getComments(@Path("movie_id") movieId: Int): Response<CommentResponse>
}