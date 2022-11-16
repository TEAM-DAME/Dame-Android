package com.yangbong.data.remote.model.response

import com.google.gson.annotations.SerializedName

data class CheckDuplicateProfileIdResponse(
    @SerializedName("available")
    val available: Boolean?
)
