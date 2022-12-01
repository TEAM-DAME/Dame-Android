package com.yangbong.domain.entity

data class NotificationInfo(
    val notificationType: Int,
    val todayEmotion: Int? = null,
    val userInfo: UserInfo
)