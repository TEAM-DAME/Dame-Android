package com.yangbong.domain.entity.response

import com.yangbong.domain.entity.UserInfo

data class DomainSetProfileResponse(
    val accessToken: String,
    val userInfo: UserInfo
)