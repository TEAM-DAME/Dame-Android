package com.yangbong.set_profile

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.yangbong.damedame.set_profile.R

class SelectCharacterAdapter(val item: ArrayList<SelectCharacterData>):RecyclerView.Adapter<SelectCharacterAdapter.ViewHolder>() {
    interface OnItemClickListener{
        fun OnItemClick(data:SelectCharacterData,imageView: ImageView)
    }

    var itemClickListener:OnItemClickListener?=null

    inner class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        val imageView = itemView.findViewById<ImageView>(R.id.item_character_image)
        val textView = itemView.findViewById<TextView>(R.id.item_character_name)

        init{
            imageView.setOnClickListener {
                itemClickListener?.OnItemClick(item[adapterPosition],imageView)
            }
        }
    }
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SelectCharacterAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_character,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: SelectCharacterAdapter.ViewHolder, position: Int) {
        holder.textView.text = item[position].characterName
        when(item[position].characterName){
            "구름이" -> holder.imageView.setImageResource(com.yangbong.damedame.shared.R.drawable.img_character_01)
            "몽실이" -> holder.imageView.setImageResource(com.yangbong.damedame.shared.R.drawable.img_character_02)
            "솜사탕" -> holder.imageView.setImageResource(com.yangbong.damedame.shared.R.drawable.img_character_03)
            "개나리" -> holder.imageView.setImageResource(com.yangbong.damedame.shared.R.drawable.img_character_04)
            "까망이" -> holder.imageView.setImageResource(com.yangbong.damedame.shared.R.drawable.img_character_05)
            "토순이" -> holder.imageView.setImageResource(com.yangbong.damedame.shared.R.drawable.img_character_06)
            "뿡뿡이" -> holder.imageView.setImageResource(com.yangbong.damedame.shared.R.drawable.img_character_07)
            "째깐이" -> holder.imageView.setImageResource(com.yangbong.damedame.shared.R.drawable.img_character_08)
            "팬둥이" -> holder.imageView.setImageResource(com.yangbong.damedame.shared.R.drawable.img_character_09)
        }
    }

    override fun getItemCount(): Int {
        return item.size
    }
}