package com.yangbong.damedame.notification

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.yangbong.damedame.notification.databinding.RecyclerItemNotificationBinding

class NotificationRecyclerViewAdapter(private val items: List<Int>) :
    RecyclerView.Adapter<NotificationRecyclerViewAdapter.ViewHolder>() {
    private fun getRequestMsg(id: String) = "${id}님이 친구 요청을 보냈습니다."

    inner class ViewHolder(val binding: RecyclerItemNotificationBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            RecyclerItemNotificationBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        when (items[position]) {
            0 -> {
                holder.binding.leftItemImage.setImageResource(com.yangbong.damedame.shared.R.drawable.img_friend_request)
                holder.binding.rightItemImage.setImageResource(com.yangbong.damedame.shared.R.drawable.img_approve_friend_request)

                holder.binding.notificationText.text = getRequestMsg("홍길동")
            }
            1 -> {
                holder.binding.leftItemImage.setImageResource(com.yangbong.damedame.shared.R.drawable.img_write_diary_request)
                holder.binding.notificationText.text = "오늘의 다이어리를 작성해주세요."
                holder.binding.rightItemImage.visibility = View.GONE
            }
            2 -> {
                holder.binding.leftItemImage.setImageResource(com.yangbong.damedame.shared.R.drawable.img_emotion_positive)
                holder.binding.notificationText.text = "오늘의 감정은 긍정입니다."
                holder.binding.rightItemImage.visibility = View.GONE
            }
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }
}