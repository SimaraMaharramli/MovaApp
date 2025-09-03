package com.example.movaapp.screen.explore

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movaapp.di.MovieRepository
import com.example.movaapp.model.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class ExploreViewModel @Inject constructor(
    private val repository: MovieRepository
) : ViewModel() {

     val movies = MutableLiveData<List<Result>>()


    init {
        fetchExploreMovies()
    }

     fun fetchExploreMovies() {
        viewModelScope.launch (Dispatchers.IO){
            try {

                val movie = repository.getDiscoverMovies()
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

//    fun fetchExploreMovies(){
//        viewModelScope.launch(Dispatchers.IO) {
//
//            try {
//                val response = repository.getDiscoverMovies()
//                if(response.isSuccessful){
//                    response.body()?.results.let{
//                        withContext(Dispatchers.Main){
//                            movies.value = it
//                            Log.e("Success", it.toString())
//                        }
//                    }
//                } else {
//                    Log.e("Sehvdi", "Gozune girsin")
//                }
//
//
//            }catch (e: Exception){
//                withContext(Dispatchers.Main){
//                    Log.e("Catchdedi", e.toString())
////                    error.value = e.localizedMessage
//                }
//            }
//
//            }
//        }
}