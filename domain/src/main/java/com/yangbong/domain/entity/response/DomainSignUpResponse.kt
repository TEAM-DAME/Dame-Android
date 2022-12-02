package com.yangbong.domain.entity.response

data class DomainSignUpResponse(
    val userId: Int,
    val jwtToken: String
)