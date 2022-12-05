package com.yangbong.data.remote.model.response

import com.google.gson.annotations.SerializedName

data class Character(
    @SerializedName("minionId")
    val minionId: Int
)
