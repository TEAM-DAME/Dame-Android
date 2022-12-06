package com.yangbong.data.remote.service

import com.yangbong.data.remote.call_adapter.NetworkState
import com.yangbong.data.remote.model.response.BaseResponse
import com.yangbong.data.remote.model.response.FriendListResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface FriendsService {

    @GET("v1/user/friend/{userId}")
    suspend fun getFriendList(
        @Path("userId") userId: Int,
        @Query("page") page: Int = 1,
        @Query("size") size: Int = 20
    ): NetworkState<BaseResponse<FriendListResponse>>
}