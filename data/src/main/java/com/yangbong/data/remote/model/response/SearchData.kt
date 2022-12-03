package com.yangbong.data.remote.model.response

import com.google.gson.annotations.SerializedName

data class SearchData(
    @SerializedName("nickName")
    val nickName:String,
    @SerializedName("profileImageUrl")
    val profileImageUrl:String
)