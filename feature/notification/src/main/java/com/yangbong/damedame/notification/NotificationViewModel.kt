package com.yangbong.damedame.notification


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.yangbong.core_ui.base.BaseViewModel
import com.yangbong.damedame.notification.notification_data.NotificationData

class NotificationViewModel : BaseViewModel() {

    private val _notifications = MutableLiveData<List<NotificationData>>()
    val notifications: LiveData<List<NotificationData>>
        get() = _notifications

}