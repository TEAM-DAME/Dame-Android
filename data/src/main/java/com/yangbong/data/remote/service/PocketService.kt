package com.yangbong.data.remote.service

import com.yangbong.data.remote.call_adapter.NetworkState
import com.yangbong.data.remote.model.response.BaseResponse
import com.yangbong.data.remote.model.response.Character
import retrofit2.http.GET
import retrofit2.http.Path

interface PocketService {

    @GET("v1/user/minion/home/{userId}")
    suspend fun getCharacterList(
        @Path("userId") userId: Int
    ): NetworkState<BaseResponse<List<Character>>>
}