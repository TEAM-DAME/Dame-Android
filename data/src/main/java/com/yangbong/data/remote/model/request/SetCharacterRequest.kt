package com.yangbong.data.remote.model.request

import com.google.gson.annotations.SerializedName

data class SetCharacterRequest(
    @SerializedName("userId")
    val userId: Int,
    @SerializedName("minion")
    val minion: Int
)
