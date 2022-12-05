package com.yangbong.domain.entity.response

import com.yangbong.domain.entity.SearchInfo

data class DomainFriendListResponse(
    val next:Int?,
    val prev:Int?,
    val results:ArrayList<SearchInfo>
)
