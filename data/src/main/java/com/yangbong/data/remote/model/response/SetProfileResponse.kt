package com.yangbong.data.remote.model.response

import com.google.gson.annotations.SerializedName

data class SetProfileResponse(
    @SerializedName("accessToken")
    val accessToken: String,
    @SerializedName("user")
    val user: User,
)
