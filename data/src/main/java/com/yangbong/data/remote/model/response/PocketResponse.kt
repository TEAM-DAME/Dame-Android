package com.yangbong.data.remote.model.response

import com.google.gson.annotations.SerializedName

data class PocketResponse (
    @SerializedName("status")
    val status:Int,
    @SerializedName("message")
    val message:String,
    @SerializedName("data")
    val data:ArrayList<Int>
)