package com.yangbong.data.remote.model.response

import com.google.gson.annotations.SerializedName

data class MyProfileResponse(
    @SerializedName("userId")
    val userId: Int,
    @SerializedName("nickName")
    val nickName: String?,
    @SerializedName("profileImageUrl")
    val profileImageUrl: String?,
    @SerializedName("diaryCount")
    val diaryCount: Int,
    @SerializedName("minionCount")
    val minionCount: Int,
    @SerializedName("friendCount")
    val friendCount: Int
)