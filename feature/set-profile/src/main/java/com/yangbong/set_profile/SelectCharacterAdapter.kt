package com.yangbong.set_profile

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.yangbong.damedame.set_profile.R
import com.yangbong.damedame.set_profile.databinding.ItemCharacterBinding

class SelectCharacterAdapter(val item: ArrayList<CharacterData>):RecyclerView.Adapter<SelectCharacterAdapter.ViewHolder>() {
    interface OnItemClickListener{
        fun OnItemClick(data:CharacterData, imageView: ImageView)
    }

    var itemClickListener:OnItemClickListener?=null

    inner class ViewHolder(val binding: ItemCharacterBinding):RecyclerView.ViewHolder(binding.root){

        init{
            binding.itemCharacterName.setOnClickListener{
                itemClickListener?.OnItemClick(item[adapterPosition],binding.itemCharacterImage)
            }
        }
    }
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SelectCharacterAdapter.ViewHolder {
        val binding = ItemCharacterBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SelectCharacterAdapter.ViewHolder, position: Int) {
        holder.binding.itemCharacterName.text= item[position].characterName
        when(item[position].characterName){
            "구름이" -> holder.binding.itemCharacterImage.setImageResource(com.yangbong.damedame.shared.R.drawable.img_character_01)
            "몽실이" -> holder.binding.itemCharacterImage.setImageResource(com.yangbong.damedame.shared.R.drawable.img_character_02)
            "솜사탕" -> holder.binding.itemCharacterImage.setImageResource(com.yangbong.damedame.shared.R.drawable.img_character_03)
            "개나리" -> holder.binding.itemCharacterImage.setImageResource(com.yangbong.damedame.shared.R.drawable.img_character_04)
            "까망이" -> holder.binding.itemCharacterImage.setImageResource(com.yangbong.damedame.shared.R.drawable.img_character_05)
            "토순이" -> holder.binding.itemCharacterImage.setImageResource(com.yangbong.damedame.shared.R.drawable.img_character_06)
            "뿡뿡이" -> holder.binding.itemCharacterImage.setImageResource(com.yangbong.damedame.shared.R.drawable.img_character_07)
            "째깐이" -> holder.binding.itemCharacterImage.setImageResource(com.yangbong.damedame.shared.R.drawable.img_character_08)
            "팬둥이" -> holder.binding.itemCharacterImage.setImageResource(com.yangbong.damedame.shared.R.drawable.img_character_09)
        }
    }

    override fun getItemCount(): Int {
        return item.size
    }
}