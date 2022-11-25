package com.yangbong.damedame.notification

import android.view.LayoutInflater
import android.view.View
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
        when(items[position]){
            "설정" ->  {
                holder.binding.rightItemImage.visibility= View.GONE
                holder.binding.latestVersionText.visibility=View.VISIBLE
            }
        }
    }

    override fun getItemCount(): Int = items.size
}