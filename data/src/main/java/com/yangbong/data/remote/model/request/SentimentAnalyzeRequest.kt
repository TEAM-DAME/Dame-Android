package com.yangbong.data.remote.model.request

import com.google.gson.annotations.SerializedName

data class SentimentAnalyzeRequest(
    @SerializedName("content")
    val content: String
)
