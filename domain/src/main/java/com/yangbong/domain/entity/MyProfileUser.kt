package com.yangbong.domain.entity

data class MyProfileUser(
    var profileId: String,
    var profileImageUrl: String,
    var diaryCount: Int,
    var pocketCount: Int,
    var friendCount: Int,
    var isFriend:Boolean
)