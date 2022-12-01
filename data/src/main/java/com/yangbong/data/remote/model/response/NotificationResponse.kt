package com.yangbong.data.remote.model.response

import com.google.gson.annotations.SerializedName
import com.yangbong.domain.entity.NotificationInfo

data class NotificationResponse(
    @SerializedName("notificationData")
    val notificationData: List<Notification>
)
