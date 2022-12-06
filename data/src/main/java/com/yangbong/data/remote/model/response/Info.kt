package com.yangbong.data.remote.model.response

import com.google.gson.annotations.SerializedName

data class Info(
    @SerializedName("next")
    val next: Int?,
    @SerializedName("prev")
    val prev: Int?
)
