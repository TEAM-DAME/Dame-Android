package com.yangbong.data.remote.model.response

data class MyProfileResponse(
    val profileId: String,
    val profileImageUrl: String,
    val diaryCount: Int,
    val pocketCount: Int,
    val friendCount: Int,
    val diaries: List<Diary>
)