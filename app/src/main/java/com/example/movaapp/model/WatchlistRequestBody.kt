package com.example.movaapp.model

import com.google.gson.annotations.SerializedName

class WatchlistRequestBody( @SerializedName("media_type")
                            val mediaType: String,
                            @SerializedName("media_id")
                            val mediaId: Int,
                            val favorite: Boolean)