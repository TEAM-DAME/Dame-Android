package com.yangbong.data.remote.model.response

import com.google.gson.annotations.SerializedName

data class DiaryResponse(
    @SerializedName("minionId")
    val minionId: Int,
    @SerializedName("title")
    val title: String?,
    @SerializedName("content")
    val content: String?,
    @SerializedName("createdAt")
    val createdAt: String?
)