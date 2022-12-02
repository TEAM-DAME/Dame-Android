package com.yangbong.data.remote.model.request

import com.google.gson.annotations.SerializedName

data class WriteDiaryRequest(
    @SerializedName("userId")
    val userId: Int,
    @SerializedName("title")
    val title: String,
    @SerializedName("content")
    val content: String,
    @SerializedName("emotion")
    val emotion: EmotionRequest
)

data class EmotionRequest(
    @SerializedName("positive")
    val positive: Double,
    @SerializedName("neutral")
    val neutral: Double,
    @SerializedName("negative")
    val negative: Double
)
