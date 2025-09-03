package com.example.movaapp.screen.watchList

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movaapp.di.MovieRepository
import com.example.movaapp.local.WatchListDao
import com.example.movaapp.model.Result
import com.example.movaapp.model.WatchListModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class WatchListViewModel  @Inject constructor(
//    private val repository: MovieRepository,
    private val watchlitsdao: WatchListDao
): ViewModel() {
//    val movies = MutableLiveData<List<Result>?>()
//    val success = MutableLiveData<Boolean>()
val id=MutableLiveData<Int>()
val allTodos=watchlitsdao.getAllTodos()

//    fun getList() {
//        viewModelScope.launch {
//            try {
//                var response=watchlitsdao.getAllTodos()
//
//                allTodos.value=response.collect()
//                Log.e("ssssss",response.toString())
//            } catch (e: Exception) {
//                // Handle the exception
//            }
//        }
//    }

    fun addList(todo: WatchListModel) {
        viewModelScope.launch {
            try {
                watchlitsdao.addList(todo)
            } catch (e: Exception) {
                // Handle the exception
            }
        }
    }
    fun getbyid(mid: Int) {
        viewModelScope.launch {
            try {
                val response=watchlitsdao.getMovieById(mid )
                response?.let {id.value=response.moveid  }

            } catch (e: Exception) {
                // Handle the exception
            }
        }
    }

    fun deleteTodo(todo: WatchListModel) {
        viewModelScope.launch {
            watchlitsdao.deleteTodo(todo)
        }
    }



//    fun fetchExploreMovies() {
//        viewModelScope.launch (Dispatchers.IO){
//            try {
//
//                val movie = repository.getWatchListMovieDetails()
//                withContext(Dispatchers.Main){
//
//                        movies.value=movie
//                            Log.e("Salmmmm", movie.toString())
//
//
//                }
//
//
//            } catch (e: Exception) {
//                withContext(Dispatchers.Main){
//                    Log.e("Salam", e.toString())
//
//                }
//
//            }
//        }
//    }
//
//
//    fun addMovieToWatchlist(accountId: Int, mediaId: Int) {
//        viewModelScope.launch {
//            withContext(Dispatchers.Main) {
//                val response = repository.addToWatchlist(accountId, mediaId)
//                response?.let {
//                    success.value = response.success
//                }
//            }
//        }
//    }
}