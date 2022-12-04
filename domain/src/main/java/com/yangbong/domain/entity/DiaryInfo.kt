package com.yangbong.domain.entity

data class DiaryInfo(
    val diaryId: Int,
    val userId: Int,
    val title: String,
    val content: String,
    val positive: Double,
    val neutral: Double,
    val negative: Double,
    val visibility: Int,
    val diaryTime: String,
    val minionId: Int
)