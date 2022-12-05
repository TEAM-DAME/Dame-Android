package com.yangbong.main.friends

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.CircleCropTransformation
import com.yangbong.damedame.main.databinding.ItemFriendBinding
import com.yangbong.damedame.shared.R
import com.yangbong.domain.entity.SearchInfo

class FriendsRecyclerViewAdapter(var items:List<SearchInfo>):RecyclerView.Adapter<FriendsRecyclerViewAdapter.ViewHolder>() {
    inner class ViewHolder(val binding:ItemFriendBinding):RecyclerView.ViewHolder(binding.root){
        init {
            binding.btnVisit.setOnClickListener {
                friendItemClickListener?.onItemClick(adapterPosition)

            }
        }
    }

    var friendItemClickListener: OnItemClickListener?=null
    interface OnItemClickListener{
        fun onItemClick(position:Int){}
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding:ItemFriendBinding=ItemFriendBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.tvFriendProfileNickname.text=items[position].nickName
        holder.binding.ivFriendProfileImage.load(items[position].profileImageUrl){
            transformations(CircleCropTransformation())
            error(R.color.transparent)
            placeholder(R.color.transparent)
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }
}