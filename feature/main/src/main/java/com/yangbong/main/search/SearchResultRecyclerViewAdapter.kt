package com.yangbong.main.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.yangbong.damedame.main.databinding.SearchResultRowBinding

class SearchResultRecyclerViewAdapter(val items:ArrayList<SearchData>):RecyclerView.Adapter<SearchResultRecyclerViewAdapter.ViewHolder>() {
    inner class ViewHolder(val binding: SearchResultRowBinding):RecyclerView.ViewHolder(binding.root){
        init{
            binding.friendProfileButtonImage.setOnClickListener {
                SRitemClickListener?.onItemClick(adapterPosition)

            }
        }
    }
    var SRitemClickListener: onItemClickListener?=null
    interface onItemClickListener{
        fun onItemClick(position:Int){}
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding:SearchResultRowBinding=SearchResultRowBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.friendNameText.text=items[position].nickname
        //holder.binding.friendProfileButtonImage. 받아와서 프로필이미지 출력
    }

    override fun getItemCount(): Int {
        return items.size
    }
}