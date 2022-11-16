package com.yangbong.damedame.notification

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.yangbong.damedame.notification.databinding.RecyclerItemSettingBinding

class SettingRecyclerViewAdapter(private val items: List<String>) : RecyclerView.Adapter<SettingRecyclerViewAdapter.ViewHolder>() {
    inner class ViewHolder(val binding: RecyclerItemSettingBinding)  : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            RecyclerItemSettingBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.settingType.text=items[position]
    }

    override fun getItemCount(): Int = items.size
}