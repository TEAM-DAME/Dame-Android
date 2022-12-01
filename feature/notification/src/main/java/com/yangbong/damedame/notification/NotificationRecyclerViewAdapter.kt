package com.yangbong.damedame.notification

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.yangbong.damedame.notification.databinding.RecyclerItemNotificationBinding
import com.yangbong.damedame.notification.data.NotificationData

class NotificationRecyclerViewAdapter(var notifications: List<NotificationData>?) :
    RecyclerView.Adapter<NotificationRecyclerViewAdapter.ViewHolder>() {

    inner class ViewHolder(
        val binding: RecyclerItemNotificationBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(eachNotification: NotificationData) {
            binding.notificationData = eachNotification
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
        notifications?.get(position)?.let { holder.bind(it) }
    }

    override fun getItemCount(): Int {
        return notifications?.size?: 0
    }
}