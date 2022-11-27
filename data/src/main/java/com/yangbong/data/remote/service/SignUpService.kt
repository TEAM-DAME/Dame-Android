package com.yangbong.data.remote.service

import com.yangbong.data.remote.call_adapter.NetworkState
import com.yangbong.data.remote.model.request.SignUpRequest
import com.yangbong.data.remote.model.response.BaseResponse
import com.yangbong.data.remote.model.response.SignUpResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface SetProfileService {

    @POST("user/signup")
    suspend fun postSetProfile(
        @Body body: SignUpRequest
    ): NetworkState<BaseResponse<SignUpResponse>>
}