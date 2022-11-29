package com.yangbong.damedame.notification.notification_data

data class NotificationData(
    val notificationType: NotificationType,
    val notificationMsg: String,
    val friendsId: String = "",
    val todayEmotion: EmotionType = EmotionType.POSITIVE
)

