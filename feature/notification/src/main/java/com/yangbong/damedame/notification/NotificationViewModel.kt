package com.yangbong.damedame.notification

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.yangbong.damedame.notification.notification_data.NotificationData

class NotificationViewModel : ViewModel() {
    private val receivedNotifications = mutableListOf<NotificationData>()

    private val _notifications = MutableLiveData<List<NotificationData>>()
    val notifications: LiveData<List<NotificationData>>
        get() = _notifications

    fun addNotifications(notification: NotificationData) {
        //서버 통신을 이용해 현재 나에게 온 알림들을 추가하기
        receivedNotifications.add(notification)
        _notifications.value = receivedNotifications
    }
}