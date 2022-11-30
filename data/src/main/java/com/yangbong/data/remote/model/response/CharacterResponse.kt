package com.yangbong.data.remote.model.response

import com.google.gson.annotations.SerializedName

data class CharacterResponse(
    @SerializedName("minion")
    val characterId: Int,
    @SerializedName("exp")
    val characterExp: Int,
    @SerializedName("notice")
    val isNotification: Boolean
)