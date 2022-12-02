package com.yangbong.data.remote.service

import com.yangbong.data.remote.call_adapter.NetworkState
import com.yangbong.data.remote.model.request.SignUpRequest
import com.yangbong.data.remote.model.response.BaseResponse
import com.yangbong.data.remote.model.response.SignUpResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface SignUpService {

    @POST("v1/auth/sign-up")
    suspend fun postSignUp(
        @Body body: SignUpRequest
    ): NetworkState<BaseResponse<SignUpResponse?>>
}