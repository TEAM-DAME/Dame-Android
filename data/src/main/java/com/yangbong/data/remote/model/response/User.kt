package com.yangbong.data.remote.model.response

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("profileId")
    val profileId: String?,
    @SerializedName("profileImageUrl")
    val profileImageUrl: String?
)
