package com.yangbong.data.remote.model.response

import com.google.gson.annotations.SerializedName

data class EmptyResponse(
    @SerializedName("status")
    val status: Int,
    @SerializedName("message")
    val message: String
)