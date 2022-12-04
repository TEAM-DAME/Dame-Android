package com.yangbong.domain.entity

data class MyProfileInfo(
    val userId: Int,
    val nickName: String,
    val profileImageUrl: String,
    val diaryCount: Int,
    val minionCount: Int,
    val friendCount: Int
)