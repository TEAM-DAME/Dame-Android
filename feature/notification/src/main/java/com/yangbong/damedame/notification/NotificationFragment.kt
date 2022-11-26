package com.yangbong.damedame.notification

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.yangbong.damedame.notification.databinding.FragmentNotificationBinding
import com.yangbong.damedame.notification.notification_data.EmotionType
import com.yangbong.damedame.notification.notification_data.NotificationData
import com.yangbong.damedame.notification.notification_data.NotificationType


class NotificationFragment : Fragment() {
    private lateinit var adapter: NotificationRecyclerViewAdapter
    private var binding: FragmentNotificationBinding? = null
    private val notificationViewModel: NotificationViewModel by activityViewModels()
    private var notifications = listOf<NotificationData>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentNotificationBinding.inflate(layoutInflater)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initData()
        initRecyclerView()
    }

    private fun initRecyclerView() {
        binding!!.notificationRecycler.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding!!.notificationRecycler.adapter = adapter
    }

    private fun initData() {
        notificationViewModel.notifications.observe(requireActivity()) { receivedNotifications ->
            notifications = receivedNotifications.toList()
            adapter.notifyDataSetChanged()
        }

//        //EXAMPLE
//        adapter = NotificationRecyclerViewAdapter(
//            listOf(
//                NotificationData(
//                    notificationType = NotificationType.EMOTION,
//                    todayEmotion = EmotionType.POSITIVE
//                ),
//                NotificationData(
//                    notificationType = NotificationType.REQUEST_WRITING_DIARY,
//                ),
//                NotificationData(
//                    notificationType = NotificationType.REQUEST_FRIEND,
//                    friendsId = "boogiwoogi"
//                ),
//            )
//        )

    }
}
