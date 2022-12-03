package com.yangbong.data.remote.service

import com.yangbong.data.remote.call_adapter.NetworkState
import com.yangbong.data.remote.model.response.BaseResponse
import com.yangbong.data.remote.model.response.UserProfileResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface UserProfileService {
    @GET("v1/user/profile/{userId}")
    suspend fun getUserProfile(
        @Path("userId") userId:Int
    ):NetworkState<BaseResponse<UserProfileResponse>>
}