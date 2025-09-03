package com.example.movaapp.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "watchlist")

data class WatchListModel (
    val title :String,
    val image :String,
    val moveid:Int,
    @PrimaryKey(autoGenerate = true)
    val id:Int=0,
)