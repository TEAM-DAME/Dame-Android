package com.yangbong.data.remote.model.request

import com.google.gson.annotations.SerializedName

data class SelectCharacterRequest(
    @SerializedName("userid")
    val userid: Int,
    @SerializedName("minion")
    val minion: Int
)