package com.yangbong.damedame.notification

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.yangbong.core_ui.base.BindingFragment
import com.yangbong.damedame.notification.databinding.FragmentNotificationBinding
import com.yangbong.damedame.notification.data.NotificationData


class NotificationFragment :
    BindingFragment<FragmentNotificationBinding>(R.layout.fragment_notification) {


    private val notificationViewModel: NotificationViewModel by activityViewModels()
    private var notifications = listOf<NotificationData>()
    private var adapter = NotificationRecyclerViewAdapter(notifications)


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
            adapter.notifications = receivedNotifications
            adapter.notifyDataSetChanged()
        }
    }
}
