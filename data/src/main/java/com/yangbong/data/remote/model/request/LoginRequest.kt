package com.yangbong.data.remote.model.request

import com.google.gson.annotations.SerializedName

data class LoginRequest(
    @SerializedName("socialToken")
    val socialToken: String,
    @SerializedName("fcmToken")
    val fcmToken: String,
)

