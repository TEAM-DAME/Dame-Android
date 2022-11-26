package com.yangbong.domain.entity.response

import com.yangbong.domain.entity.UserInfo

data class DomainNotificationResponse (
    val msg: String,
    val userInfo: List<UserInfo>
)