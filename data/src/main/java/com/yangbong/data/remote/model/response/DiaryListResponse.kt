package com.yangbong.data.remote.model.response

import com.google.gson.annotations.SerializedName

data class DiaryListResponse(
    @SerializedName("info")
    val info: Info,
    @SerializedName("results")
    val results: List<Diary>
)

data class Diary(
    @SerializedName("diaryId")
    val diaryId: Int,
    @SerializedName("userId")
    val userId: Int,
    @SerializedName("title")
    val title: String,
    @SerializedName("content")
    val content: String,
    @SerializedName("positive")
    val positive: Double,
    @SerializedName("neutral")
    val neutral: Double,
    @SerializedName("negative")
    val negative: Double,
    @SerializedName("visibility")
    val visibility: Int,
    @SerializedName("diaryTime")
    val diaryTime: String,
    @SerializedName("minionId")
    val minionId: Int
)