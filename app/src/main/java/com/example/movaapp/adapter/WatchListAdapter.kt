package com.example.movaapp.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.movaapp.databinding.ItemExploreBinding
import com.example.movaapp.databinding.ItemMyListBinding
import com.example.movaapp.model.Result
import com.example.movaapp.model.WatchListModel

class WatchListAdapter : RecyclerView.Adapter<WatchListAdapter.TopMoviesViewHolder>() {


    private val movieList = arrayListOf<WatchListModel>()
//    private var onItemClickListener: ((Result) -> Unit)? = null

    class TopMoviesViewHolder(val itemTopMoviesBinding: ItemMyListBinding):
        RecyclerView.ViewHolder(itemTopMoviesBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopMoviesViewHolder {
        val view = ItemMyListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TopMoviesViewHolder(view)
    }

    override fun getItemCount(): Int {
        return movieList.size
    }

    override fun onBindViewHolder(holder: TopMoviesViewHolder, position: Int) {
        val item = movieList[position]
        holder.itemTopMoviesBinding.movie = item
        Log.e("Salmmmm", item.toString())

    }


//    fun setOnItemClickListener(listener: (Result) -> Unit) {
//        onItemClickListener = listener
//    }

    fun updateList(newList: List<WatchListModel>){
        movieList.clear()
        movieList.addAll(newList)
        notifyDataSetChanged()
    }
}