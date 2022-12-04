package com.yangbong.domain.entity.request

data class DomainDiaryRequest(
    val userId: Int,
    val title: String,
    val content: String,
    val emotionValue: Double
)

// TODO ("급하게 코드를 짜서 그런지, emotion 부분에 대한 로직이 이상하다. 추후 꼭 수정!")