package com.yangbong.data.remote.model.response

import com.google.gson.annotations.SerializedName

data class Diary(
    @SerializedName("id")
    val id: Int,
    @SerializedName("emotion")
    val emotion: Emotion,
    @SerializedName("title")
    val title: String,
    @SerializedName("date")
    val date: String,
    @SerializedName("isLocked")
    val isLocked: Boolean
)