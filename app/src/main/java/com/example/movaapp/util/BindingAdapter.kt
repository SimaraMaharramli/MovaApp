package com.example.movaapp.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.example.movaapp.R

@BindingAdapter("loadImage")
fun loadImage(view: ImageView, url: String?) {

    url?.let {
        val fullUrl ="https://image.tmdb.org/t/p/original" + it

        Glide.with(view.context)
            .load(fullUrl)
            .into(view)
    }
}

@BindingAdapter("load_url_youtube")
fun loadImageYoutube(imageView: ImageView, key: String?) {

        key?.let {
            val youtubeUrl = "https://img.youtube.com/vi/"+it+"/hqdefault.jpg"
            imageView.setImageResource(R.drawable.image)
            Glide.with(imageView.context)
                .load(youtubeUrl)
                .into(imageView)
        }
}