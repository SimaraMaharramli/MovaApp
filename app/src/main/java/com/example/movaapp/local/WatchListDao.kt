package com.example.movaapp.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.movaapp.model.WatchListModel
import kotlinx.coroutines.flow.Flow

@Dao
interface WatchListDao {
    @Insert
    suspend fun addList(todo:WatchListModel)

    @Query("SELECT * FROM watchlist ORDER BY id DESC")
    fun getAllTodos(): Flow<List<WatchListModel>>
    @Delete
    suspend fun deleteTodo(todo: WatchListModel)
    @Query("SELECT * FROM watchlist WHERE moveid = :moveId")
    suspend fun getMovieById(moveId: Int): WatchListModel?
}