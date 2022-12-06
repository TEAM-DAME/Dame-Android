package com.yangbong.data.remote.model.response

import com.google.gson.annotations.SerializedName

data class FriendListResponse(
    @SerializedName("info")
    val info: Info,
    @SerializedName("results")
    val results: List<Friend>
)

data class Friend(
    @SerializedName("userId")
    val userId: Int?,
    @SerializedName("userProfileImageUrl")
    val userProfileImageUrl: String?,
    @SerializedName("userNickname")
    val userNickname: String?
)