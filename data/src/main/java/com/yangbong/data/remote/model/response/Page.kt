package com.yangbong.data.remote.model.response

import com.google.gson.annotations.SerializedName

data class Page(
    @SerializedName("next")
    val next: Int?,
    @SerializedName("prev")
    val prev: Int?

)
