package com.example.movaapp.adapter

import com.example.movaapp.databinding.ItemTrailerBinding
import com.example.movaapp.model.ResultX


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class TrailerAdapter: RecyclerView.Adapter<TrailerAdapter.TrailerViewHolder>() {
    private val trailerList = arrayListOf<ResultX>()

    var onItemClick: ((ResultX) -> Unit)? = null

    class TrailerViewHolder(val itemTrailerBinding: ItemTrailerBinding):
        RecyclerView.ViewHolder(itemTrailerBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrailerViewHolder {
        val view = ItemTrailerBinding.inflate(LayoutInflater.from(parent.context), parent,false)
        return TrailerViewHolder(view)
    }

    override fun getItemCount(): Int {
        return trailerList.size
    }

    override fun onBindViewHolder(holder: TrailerViewHolder, position: Int) {
        val item = trailerList[position]
        holder.itemTrailerBinding.trailer = item
        holder.itemTrailerBinding.cvTrailer.setOnClickListener {
            onItemClick?.invoke(item)
        }

    }

    fun updateList(newList: List<ResultX>){
        trailerList.clear()
        trailerList.addAll(newList)
        notifyDataSetChanged()
        }
}