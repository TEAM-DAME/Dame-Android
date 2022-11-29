package com.yangbong.domain.entity.request

import com.yangbong.domain.entity.EmotionInfo

data class DomainWriteDiaryRequest (
    val userId:Int,
    val title:String,
    val content:String,
    val emotion:Emotion

)
data class Emotion(
    val positive:Double,
    val neutral:Double,
    val negative:Double
)