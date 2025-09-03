package com.example.movaapp.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.movaapp.model.WatchListModel

@Database(entities = [WatchListModel ::class], version = 1)
 abstract class MovieDatabase : RoomDatabase() {
    abstract fun createTodoListDao():WatchListDao
}