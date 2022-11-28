package com.yangbong.data.remote.service

import com.yangbong.data.remote.call_adapter.NetworkState
import com.yangbong.data.remote.model.response.BaseResponse
import com.yangbong.data.remote.model.response.CheckDuplicateProfileNicknameResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface CheckDuplicateProfileNicknameService {

    @GET("v1/auth/validate/{profileName}")
    suspend fun checkDuplicateProfileNickname(
        @Path("profileName") profileName: String
    ): NetworkState<BaseResponse<CheckDuplicateProfileNicknameResponse>>
}
