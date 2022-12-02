package com.yangbong.domain.entity.request

data class DomainDiaryRequest(
    val userId: Int,
    val title: String,
    val content: String,
    val emotionValue: EmotionValue
)

data class EmotionValue(
    val positive: Double,
    val neutral: Double,
    val negative: Double
)