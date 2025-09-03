package com.example.movaapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.movaapp.databinding.ItemCastBinding
import com.example.movaapp.model.Cast

class CastAdapter : RecyclerView.Adapter<CastAdapter.CastViewHolder>(){
    private val castList = arrayListOf<Cast>()

    class CastViewHolder(val itemCastBinding: ItemCastBinding) :
        RecyclerView.ViewHolder(itemCastBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CastViewHolder {
        val view = ItemCastBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CastViewHolder(view)
    }

    override fun getItemCount(): Int {
        return castList.size
    }

    override fun onBindViewHolder(holder: CastViewHolder, position: Int) {
        val item = castList[position]
        holder.itemCastBinding.cast = item

    }

    fun updateList(newList: List<Cast>){
        castList.clear()
        castList.addAll(newList)
        notifyDataSetChanged()
      }

}