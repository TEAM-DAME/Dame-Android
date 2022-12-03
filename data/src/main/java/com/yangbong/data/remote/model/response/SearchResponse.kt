package com.yangbong.data.remote.model.response

import com.google.gson.annotations.SerializedName

data class SearchResponse(
    @SerializedName("searchData")
    val searchData:List<SearchData>
)

