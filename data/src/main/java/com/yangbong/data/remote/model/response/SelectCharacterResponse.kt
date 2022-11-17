package com.yangbong.data.remote.model.response

import com.google.gson.annotations.SerializedName

data class SelectCharacterResponse(
    @SerializedName("message")
    val message: String,
)
