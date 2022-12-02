package com.yangbong.data.remote.model.response

import com.google.gson.annotations.SerializedName

data class Emotion(
    @SerializedName("emotionType")
    val emotionType: String,
    @SerializedName("emotionValue")
    val emotionValue: Int
)