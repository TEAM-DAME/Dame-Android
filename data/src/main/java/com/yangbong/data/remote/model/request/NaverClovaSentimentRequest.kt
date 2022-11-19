package com.yangbong.data.remote.model.request

import com.google.gson.annotations.SerializedName

data class NaverClovaSentimentRequest(
    @SerializedName("content")
    val content: String
)
