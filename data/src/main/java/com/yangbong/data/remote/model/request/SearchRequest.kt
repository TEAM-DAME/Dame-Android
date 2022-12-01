package com.yangbong.data.remote.model.request

import com.google.gson.annotations.SerializedName

data class SearchRequest(
    @SerializedName("content")
    val searchContent:String
)

