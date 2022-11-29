package com.yangbong.data.remote.model.response

import com.google.gson.annotations.SerializedName

data class Notification(
    @SerializedName("notificationType")
    val notificationType:Int,
    @SerializedName("todayEmotion")
    val todayEmotion: Int,
    @SerializedName("profileImageUrl")
    val profileImageUrl: String,
    @SerializedName("profileId")
    val profileId: String
)
