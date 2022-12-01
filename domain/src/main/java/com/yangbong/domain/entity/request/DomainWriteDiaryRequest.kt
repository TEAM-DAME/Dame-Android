package com.yangbong.domain.entity.request

data class DomainWriteDiaryRequest (
    val userId:Int,
    val title:String,
    val content:String,
    val positive:Double,
    val neutral:Double,
    val negative:Double

)

