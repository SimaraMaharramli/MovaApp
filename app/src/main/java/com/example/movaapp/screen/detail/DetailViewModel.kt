package com.example.movaapp.screen.detail

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movaapp.di.MovieRepository
import com.example.movaapp.model.Cast
import com.example.movaapp.model.MovieCreditsResponse
import com.example.movaapp.model.MovieDetailsResponse
import com.example.movaapp.model.Result
import com.example.movaapp.model.ResultX
import com.example.movaapp.model.ResultXX
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject
@HiltViewModel
class DetailViewModel @Inject constructor(
    private val repository: MovieRepository
) : ViewModel() {
    val movies = MutableLiveData<MovieDetailsResponse?>()
    val moviescast = MutableLiveData<List<Cast?>>()
    val videos = MutableLiveData<List<ResultX>>()
    val similr = MutableLiveData<List<Result>>()
    val comments  = MutableLiveData<List<ResultXX>>()
    val error = MutableLiveData<String>()
    fun fetchDetailMovies(id:Int) {
        viewModelScope.launch (Dispatchers.IO){
            try {

                val movie = repository.getMovieDetails(id)
                withContext(Dispatchers.Main){
                    movies.value=movie
                    Log.e("Salmmmm", movie.toString())
                }


            } catch (e: Exception) {
                withContext(Dispatchers.Main){
                    Log.e("Salam", e.toString())

                }

            }
        }
    }
    fun fetchCreditsMovies(id:Int) {
        viewModelScope.launch (Dispatchers.IO){
            try {

                val movie = repository.getMovieCredits(id)
                withContext(Dispatchers.Main){
                    moviescast.value=movie
                    Log.e("Salmmmm", movie.toString())
                }


            } catch (e: Exception) {
                withContext(Dispatchers.Main){
                    Log.e("Salam", e.toString())

                }

            }
        }
    }


      fun getVideos(id:Int){
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response=repository.getVideos(id)

                        withContext(Dispatchers.Main){
                            videos.value = response
                        }



            }catch (e: Exception){
                withContext(Dispatchers.Main){
                    error.value = e.localizedMessage

                }
            }

        }
    }
      fun getsimilr(id:Int){
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response=repository.getsimilr(id)

                        withContext(Dispatchers.Main){
                            similr.value = response
                        }



            }catch (e: Exception){
                withContext(Dispatchers.Main){
                    error.value = e.localizedMessage

                }
            }

        }
    }
      fun getComments(id:Int){
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response=repository.getComments(id)

                        withContext(Dispatchers.Main){
                            comments .value = response
                        }



            }catch (e: Exception){
                withContext(Dispatchers.Main){
                    error.value = e.localizedMessage

                }
            }

        }
    }




}