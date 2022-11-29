package com.yangbong.data.remote.model.request
import com.google.gson.annotations.SerializedName


data class WriteDiaryRequest(
    @SerializedName("userId")
    val userId: Int,
    @SerializedName("title")
    val title: String,
    @SerializedName("content")
    val content: String,
    @SerializedName("positive")
    val positive: Number,
    @SerializedName("neural")
    val neural: Number,
    @SerializedName("negative")
    val negative: Number


)
