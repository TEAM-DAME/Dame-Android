package com.yangbong.main.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.CircleCropTransformation
import com.yangbong.damedame.main.databinding.SearchResultRowBinding
import com.yangbong.damedame.shared.R
import com.yangbong.domain.entity.SearchInfo

class SearchResultRecyclerViewAdapter(var items:List<SearchInfo>):RecyclerView.Adapter<SearchResultRecyclerViewAdapter.ViewHolder>() {
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
        holder.binding.friendNameText.text=items[position].nickName
        holder.binding.friendProfileImage.load(items[position].profileImageUrl){
            transformations(CircleCropTransformation())
            error(R.color.transparent)
            placeholder(R.color.transparent)
        }

    }

    override fun getItemCount(): Int {
        return items.size
    }
}