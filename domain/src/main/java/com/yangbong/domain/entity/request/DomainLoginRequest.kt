package com.yangbong.domain.entity.request

data class DomainLoginRequest(
    val socialToken: String,
    val fcmToken: String
)
