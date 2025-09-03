package com.example.movaapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.movaapp.databinding.ItemSearchResultBinding
import com.example.movaapp.model.Result

class SearchAdapter : RecyclerView.Adapter<SearchAdapter.SearchViewHolder>() {

    private val searchResults = mutableListOf<Result>()

    class SearchViewHolder(private val binding: ItemSearchResultBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(movie: Result) {
            binding.movie = movie
            binding.executePendingBindings()

            // Poster resmini yüklemek için (Glide örneği)
            // val posterUrl = "https://image.tmdb.org/t/p/w500${movie.posterPath}"
            // Glide.with(binding.imageViewPoster.context)
            //     .load(posterUrl)
            //     .placeholder(R.drawable.placeholder_image) // Yer tutucu resim
            //     .into(binding.imageViewPoster)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemSearchResultBinding.inflate(inflater, parent, false)
        return SearchViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        holder.bind(searchResults[position])
    }

    override fun getItemCount(): Int = searchResults.size

    fun updateList(newList: List<Result>) {
        searchResults.clear()
        searchResults.addAll(newList)
        notifyDataSetChanged()
    }
}