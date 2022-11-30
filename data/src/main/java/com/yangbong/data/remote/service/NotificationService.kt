package com.yangbong.data.remote.service

import com.yangbong.data.remote.call_adapter.NetworkState
import com.yangbong.data.remote.model.response.BaseResponse
import com.yangbong.data.remote.model.response.EmptyResponse
import com.yangbong.data.remote.model.response.MyProfileResponse
import com.yangbong.data.remote.model.response.NotificationResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface NotificationService {

    @GET("user/{userId}")
    suspend fun getNotification(
        @Path("userId") userId: Int
    ): NetworkState<BaseResponse<NotificationResponse>>

    @GET("user/{userId}")
    suspend fun deleteNotification(
        @Path("userId") userId: Int
    ): NetworkState<EmptyResponse>
}