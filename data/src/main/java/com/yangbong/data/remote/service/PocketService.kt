package com.yangbong.data.remote.service

import com.yangbong.data.remote.call_adapter.NetworkState
import com.yangbong.data.remote.model.response.PocketResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface PocketService {
    @GET("v1/user/minion/home/{userid}")
    suspend fun getUserPocket(
        @Path("userId") userId:Int
    ):NetworkState<PocketResponse>
}