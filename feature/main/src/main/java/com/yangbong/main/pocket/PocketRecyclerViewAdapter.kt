package com.yangbong.main.pocket

import android.content.ClipData
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.yangbong.damedame.main.R
import com.yangbong.damedame.main.databinding.ItemCharacterBinding
import com.yangbong.domain.entity.CharacterInfo

class PocketRecyclerViewAdapter(var items:ArrayList<CharacterInfo>):RecyclerView.Adapter<PocketRecyclerViewAdapter.ViewHolder>() {
    inner class ViewHolder(val binding:ItemCharacterBinding):RecyclerView.ViewHolder(binding.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding:ItemCharacterBinding= ItemCharacterBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.binding.userCharacterData?.characterId=items[position].characterId
    }

    override fun getItemCount(): Int {
        return items.size
    }
}