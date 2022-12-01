package com.yangbong.damedame.notification.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.yangbong.damedame.notification.R
import com.yangbong.damedame.notification.data.EmotionType
import com.yangbong.damedame.notification.data.NotificationData
import com.yangbong.damedame.notification.data.NotificationType

@BindingAdapter("setNotificationImage")
fun ImageView.setNotificationImage(notificationData: NotificationData) {
    val imageSrc = when (notificationData.notificationInfo.notificationType) {
        NotificationType.REQUEST_FRIEND.notificationTypeNumber -> com.yangbong.damedame.shared.R.drawable.img_approve_friend_request
        NotificationType.REQUEST_WRITING_DIARY.notificationTypeNumber -> com.yangbong.damedame.shared.R.drawable.img_write_diary_request
        else -> notificationData.notificationInfo.todayEmotion!!.toEmotionImageSrc()
    }

    this.setImageResource(imageSrc)
}

fun Int.toEmotionImageSrc(): Int {
    return when(this){
        EmotionType.POSITIVE.emotionTypeNumber -> com.yangbong.damedame.shared.R.drawable.img_emotion_positive
        EmotionType.NEUTRAL.emotionTypeNumber -> com.yangbong.damedame.shared.R.drawable.ic_neutral
        else -> com.yangbong.damedame.shared.R.drawable.ic_negative
    }
}