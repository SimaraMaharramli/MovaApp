package com.example.movaapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.movaapp.databinding.ItemExploreBinding
import com.example.movaapp.databinding.ItemTopMoviesBinding
import com.example.movaapp.model.Result

class ExploreAdapter : RecyclerView.Adapter<ExploreAdapter.TopMoviesViewHolder>() {


    private val movieList = arrayListOf<Result>()
    private var onItemClickListener: ((Result) -> Unit)? = null

    class TopMoviesViewHolder(val itemTopMoviesBinding: ItemExploreBinding):
        RecyclerView.ViewHolder(itemTopMoviesBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopMoviesViewHolder {
        val view = ItemExploreBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TopMoviesViewHolder(view)
    }

    override fun getItemCount(): Int {
        return movieList.size
    }

    override fun onBindViewHolder(holder: TopMoviesViewHolder, position: Int) {
        val item = movieList[position]
        holder.itemTopMoviesBinding.movie = item
        holder.itemView.setOnClickListener {
            onItemClickListener?.invoke(item)
        }
    }


    fun setOnItemClickListener(listener: (Result) -> Unit) {
        onItemClickListener = listener
    }

    fun updateList(newList: List<Result>){
        movieList.clear()
        movieList.addAll(newList)
        notifyDataSetChanged()
    }
}