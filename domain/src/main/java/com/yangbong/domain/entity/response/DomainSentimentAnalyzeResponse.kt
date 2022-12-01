package com.yangbong.domain.entity.response

data class DomainSentimentAnalyzeResponse(
    val negative:Double,
    val neutral:Double,
    val positive:Double,
    val sentiment:String
)
