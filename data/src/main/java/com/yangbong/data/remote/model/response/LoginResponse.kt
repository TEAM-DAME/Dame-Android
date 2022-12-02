package com.yangbong.data.remote.model.response

import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName("isNewUser")
    val isNewUser: Boolean
)