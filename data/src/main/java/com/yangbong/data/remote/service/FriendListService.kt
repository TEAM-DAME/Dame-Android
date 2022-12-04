package com.yangbong.data.remote.service

import com.yangbong.data.remote.call_adapter.NetworkState
import com.yangbong.data.remote.model.response.FriendListResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface FriendListService {

    @GET("v1/user/friend/{userid}")
    suspend fun getFriendList(
        @Path("userId") userId:Int,
        @Query("page") page:Int,
        @Query("size") size:Int
    ):NetworkState<FriendListResponse>
}