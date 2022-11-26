package com.yangbong.damedame.notification.notification_data

data class  NotificationData(
    val notificationType: NotificationType,
    val friendsId: String = "",
    val todayEmotion: EmotionType = EmotionType.POSITIVE
)

