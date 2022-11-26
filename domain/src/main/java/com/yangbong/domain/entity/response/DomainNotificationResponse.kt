package com.yangbong.domain.entity.response

import com.yangbong.domain.entity.UserInfo

class DomainNotificationResponse (
    val msg: String,
    val userInfo: List<UserInfo>
)