package com.yangbong.data.remote.model.response

import com.google.gson.annotations.SerializedName

data class MyProfileResponse(
    @SerializedName("nickName")
    val nickName: String,
    @SerializedName("profileImageUrl")
    val profileImageUrl: String,
    @SerializedName("diaryCount")
    val diaryCount: Int,
    @SerializedName("minionCount")
    val minionCount: Int,
    @SerializedName("friendCount")
    val friendCount: Int,
    @SerializedName("isFriend")
    val isFriend:Boolean
    //val diaries: List<Diary>
)