package com.example.movaapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.movaapp.databinding.ItemTopMoviesBinding
import com.example.movaapp.model.Result

class TopMoviesAdapter: RecyclerView.Adapter<TopMoviesAdapter.TopMoviesViewHolder>() {


    private val movieList = arrayListOf<Result>()


    class TopMoviesViewHolder(val itemTopMoviesBinding: ItemTopMoviesBinding):
        RecyclerView.ViewHolder(itemTopMoviesBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopMoviesViewHolder {
        val view = ItemTopMoviesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TopMoviesViewHolder(view)
    }

    override fun getItemCount(): Int {
        return movieList.size
    }

    override fun onBindViewHolder(holder: TopMoviesViewHolder, position: Int) {
        val item = movieList[position]
        holder.itemTopMoviesBinding.movie = item
    }

    fun updateList(newList: List<Result>){
        movieList.clear()
        movieList.addAll(newList)
        notifyDataSetChanged()
    }
}