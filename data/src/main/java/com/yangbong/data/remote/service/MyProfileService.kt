package com.yangbong.data.remote.service

import com.yangbong.data.remote.call_adapter.NetworkState
import com.yangbong.data.remote.model.response.BaseResponse
import com.yangbong.data.remote.model.response.MyProfileResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface MyProfileService {

    @GET("v1/user/profile/{userid}")
    suspend fun getMyProfile(
        @Path("userId") userId:Int
    ): NetworkState<BaseResponse<MyProfileResponse>>
}