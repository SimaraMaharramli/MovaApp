package com.example.movaapp.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("loadImage")
fun loadImage(view: ImageView, url: String?) {

    url?.let {
        val fullUrl ="https://image.tmdb.org/t/p/original" + it

        Glide.with(view.context)
            .load(fullUrl)
            .into(view)
    }
}