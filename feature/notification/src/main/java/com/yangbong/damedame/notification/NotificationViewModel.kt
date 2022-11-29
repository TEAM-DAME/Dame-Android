package com.yangbong.damedame.notification

import android.app.Application
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.yangbong.core_ui.base.BaseViewModel
import com.yangbong.core_ui.extension.getString
import com.yangbong.damedame.notification.notification_data.NotificationData
import com.yangbong.damedame.notification.notification_data.NotificationType
import com.yangbong.damedame.notification.notification_util.notificationMsg

class NotificationViewModel : BaseViewModel() {

    private val _notifications = MutableLiveData<List<NotificationData>>()
    val notifications: LiveData<List<NotificationData>>
        get() = _notifications

}