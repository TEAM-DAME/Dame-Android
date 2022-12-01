package com.yangbong.data.remote.model.response

import com.google.gson.annotations.SerializedName

data class SearchResponse(
    @SerializedName("searchData")
    val searchData:ArrayList<searchData>
)

data class searchData(
    @SerializedName("userNickName")
    val userNickName:String,
    @SerializedName("userProfileImg")
    val userProfileImg:String
)