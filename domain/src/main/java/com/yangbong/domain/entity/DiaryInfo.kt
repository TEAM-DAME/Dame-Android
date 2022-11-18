package com.yangbong.domain.entity

data class DiaryInfo(
    val id: Int,
    val emotion: EmotionInfo,
    val title: String,
    val date: String,
    val isLocked: Boolean
)