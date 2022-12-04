package com.yangbong.data.remote.model.request

import com.google.gson.annotations.SerializedName

data class SignUpRequest(
    @SerializedName("platform")
    val platform: String,
    @SerializedName("socialToken")
    val socialToken: String,
    @SerializedName("fcmToken")
    val fcmToken: String,
    @SerializedName("nickName")
    val nickName: String,
    @SerializedName("profileImageUrl")
    val profileImageUrl: String
)
