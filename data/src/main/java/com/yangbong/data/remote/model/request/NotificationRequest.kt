package com.yangbong.data.remote.model.request

import com.google.gson.annotations.SerializedName

data class NotificationRequest(
    @SerializedName("userId")
    val userId: Int
)
