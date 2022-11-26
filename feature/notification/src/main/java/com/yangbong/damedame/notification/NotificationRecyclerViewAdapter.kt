package com.yangbong.damedame.notification

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.yangbong.damedame.notification.databinding.RecyclerItemNotificationBinding
import com.yangbong.damedame.notification.notification_data.NotificationData

class NotificationRecyclerViewAdapter(private val notifications: List<NotificationData>) :
    RecyclerView.Adapter<NotificationRecyclerViewAdapter.ViewHolder>() {

    inner class ViewHolder(
        val binding: RecyclerItemNotificationBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(eachNotification : NotificationData){
            binding.notificationData
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            RecyclerItemNotificationBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(notifications[position])
    }

    override fun getItemCount(): Int {
        return notifications.size
    }
}