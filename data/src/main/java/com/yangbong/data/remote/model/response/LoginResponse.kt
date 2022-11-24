package com.yangbong.data.remote.model.response


import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName("jwt")
    val jwtToken: String,
    @SerializedName("isnewuser")
    val isNewUser: Boolean,
    @SerializedName("message")
    val message: String
)