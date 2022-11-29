package com.yangbong.damedame.notification

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.yangbong.core_ui.base.BindingFragment
import com.yangbong.damedame.notification.databinding.FragmentNotificationBinding
import com.yangbong.damedame.notification.notification_data.EmotionType
import com.yangbong.damedame.notification.notification_data.NotificationData
import com.yangbong.damedame.notification.notification_data.NotificationType


class NotificationFragment :
    BindingFragment<FragmentNotificationBinding>(R.layout.fragment_notification)
{

    private lateinit var adapter: NotificationRecyclerViewAdapter
    private val notificationViewModel: NotificationViewModel by activityViewModels()
    private var notifications = listOf<NotificationData>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initData()
        initRecyclerView()
    }

    private fun initRecyclerView() {
        binding.notificationRecycler.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.notificationRecycler.adapter = adapter
    }

    private fun initData() {
        notificationViewModel.notifications.observe(requireActivity()) { receivedNotifications ->
            notifications = receivedNotifications.toList()
            adapter.notifyDataSetChanged()
        }
    }
}
