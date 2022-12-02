package com.yangbong.data.remote.model.response

import com.google.gson.annotations.SerializedName

data class SignUpResponse(
    @SerializedName("userId")
    val userId: Int,
    @SerializedName("jwtToken")
    val jwtToken: String
)
