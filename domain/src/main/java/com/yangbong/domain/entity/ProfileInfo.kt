package com.yangbong.domain.entity

data class ProfileInfo(
    var nickName: String,
    var profileImageUrl: String,
    var diaryCount: Int,
    var minionCount: Int,
    var friendCount: Int,
    val isFriend:Boolean
)
