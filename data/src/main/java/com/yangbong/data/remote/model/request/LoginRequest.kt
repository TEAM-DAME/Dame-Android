package com.yangbong.data.remote.model.request

import com.google.gson.annotations.SerializedName

data class LoginRequest(
    @SerializedName("platform")
    val platform: String,
    @SerializedName("socialToken")
    val socialToken: String
)

