package com.yangbong.domain.entity

data class MyProfileUser(
    val userId: Int,
    var nickName: String,
    var profileImageUrl: String,
    var diaryCount: Int,
    var minionCount: Int,
    var friendCount: Int
)