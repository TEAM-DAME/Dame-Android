package com.yangbong.main.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.yangbong.damedame.main.databinding.SearchRowBinding

class FriendSearchRecyclerViewAdapter(var items: ArrayList<String>):RecyclerView.Adapter<FriendSearchRecyclerViewAdapter.ViewHolder>(){
    inner class ViewHolder(val binding:SearchRowBinding):RecyclerView.ViewHolder(binding.root){
       init{
            binding.friendNameText.setOnClickListener {
                FSitemClickListner?.onItemClick(adapterPosition)
            }
           binding.XBtn.setOnClickListener {
               FSitemClickListner?.onXClick(adapterPosition)
           }
       }

    }
    var FSitemClickListner:OnItemClickListener?=null
    interface OnItemClickListener{
        fun onItemClick(position: Int){}
        fun onXClick(position: Int){}
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding:SearchRowBinding= SearchRowBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }



    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.friendNameText.text=items[position]

    }
    override fun getItemCount(): Int {
        return items.size
    }
}