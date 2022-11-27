package com.yangbong.data.remote.model.response

import com.google.gson.annotations.SerializedName

data class CheckDuplicateProfileNicknameResponse(
    @SerializedName("available")
    val available: Boolean
)