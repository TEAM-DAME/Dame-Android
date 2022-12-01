package com.yangbong.domain.entity.response

import com.yangbong.domain.entity.NotificationInfo

data class DomainNotificationResponse (
    val notifications: List<NotificationInfo>
)