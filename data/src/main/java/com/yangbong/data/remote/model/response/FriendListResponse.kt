package com.yangbong.data.remote.model.response

import com.google.gson.annotations.SerializedName

data class FriendListResponse(
    @SerializedName("status")
    val status: Int,
    @SerializedName("message")
    val message: String,
    @SerializedName("info")
    val info: Page,
    @SerializedName("results")
    val results: ArrayList<SearchData>

)
