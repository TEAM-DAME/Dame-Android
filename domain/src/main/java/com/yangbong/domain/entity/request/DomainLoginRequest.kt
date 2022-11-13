package com.yangbong.domain.entity.request

data class DomainLoginRequest(
    val platform: String,
    val socialToken: String
)
