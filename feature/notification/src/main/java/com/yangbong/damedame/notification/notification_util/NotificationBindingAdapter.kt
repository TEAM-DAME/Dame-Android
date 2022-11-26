package com.yangbong.damedame.notification.notification_util


import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.yangbong.core_ui.extension.getString
import com.yangbong.damedame.notification.notification_data.NotificationData
import com.yangbong.damedame.notification.notification_data.NotificationType


@BindingAdapter("setNotificationText")
fun TextView.setNotificationText(notificationData: NotificationData) {
    val text = when (notificationData.notificationType) {
        NotificationType.REQUEST_FRIEND -> {
            getString(com.yangbong.damedame.shared.R.string.friend_request_msg)
                .format(notificationData.friendsId)
        }
        NotificationType.EMOTION -> {
            notificationData.todayEmotion.emotion
        }
        NotificationType.REQUEST_WRITING_DIARY->{
            getString(com.yangbong.damedame.shared.R.string.writing_request_msg)
        }
    }

    this.text = text
}

@BindingAdapter("setNotificationImage")
fun ImageView.setNotificationImage(notificationData: NotificationData) {
    val imageSrc = when (notificationData.notificationType) {
        NotificationType.REQUEST_FRIEND -> com.yangbong.damedame.shared.R.drawable.img_approve_friend_request
        NotificationType.EMOTION -> notificationData.todayEmotion.emotionImgSrc
        NotificationType.REQUEST_WRITING_DIARY -> com.yangbong.damedame.shared.R.drawable.img_write_diary_request
    }

    this.setImageResource(imageSrc)
}