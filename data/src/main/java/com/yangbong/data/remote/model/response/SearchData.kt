package com.yangbong.data.remote.model.response

import com.google.gson.annotations.SerializedName

data class SearchData(
    @SerializedName("userId")
    val userId:Int,
    @SerializedName("nickName")
    val nickName:String,
    @SerializedName("profileImageUrl")
    val profileImageUrl:String
)