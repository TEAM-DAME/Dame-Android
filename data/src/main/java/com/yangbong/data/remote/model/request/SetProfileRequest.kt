package com.yangbong.data.remote.model.request

import com.google.gson.annotations.SerializedName

data class SetProfileRequest(
    @SerializedName("profileId")
    val profileId: String,
    @SerializedName("profileImgUrl")
    val profileImgUrl: String
)
