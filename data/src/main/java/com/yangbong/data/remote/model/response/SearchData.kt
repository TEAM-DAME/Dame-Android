package com.yangbong.data.remote.model.response

import com.google.gson.annotations.SerializedName

data class SearchData(
    @SerializedName("userNickName")
    val userNickName:String,
    @SerializedName("userProfileImg")
    val userProfileImg:String
)