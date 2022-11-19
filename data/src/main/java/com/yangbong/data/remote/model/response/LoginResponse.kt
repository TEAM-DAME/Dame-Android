package com.yangbong.data.remote.model.response


import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName("jwtToken")
    val jwtToken: String,
    @SerializedName("isNewUser")
    val isNewUser: Boolean
)

