package com.yangbong.domain.entity.request

data class DomainSignUpRequest(
    val platform: String,
    val socialToken: String,
    val fcmToken: String,
    val nickname: String,
    val profileImageUrl: String
)