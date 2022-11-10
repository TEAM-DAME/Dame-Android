package com.yangbong.damedame.settings

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.yangbong.damedame.settings.databinding.ItemSettingBinding

class SettingRecyclerViewAdapter(private val items: List<String>) : RecyclerView.Adapter<SettingRecyclerViewAdapter.ViewHolder>() {
    inner class ViewHolder(val binding: ItemSettingBinding)  : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemSettingBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.settingType.text=items[position]
    }

    override fun getItemCount(): Int = items.size
}