package com.yangbong.domain.entity

data class MyProfileInfo(
    val myProfileUser: MyProfileUser,
    val diaryList: List<DiaryInfo>
)