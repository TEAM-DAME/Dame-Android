package com.yangbong.data.remote.data_source

import com.yangbong.data.remote.call_adapter.NetworkState
import com.yangbong.data.remote.model.request.SignUpRequest
import com.yangbong.data.remote.model.response.BaseResponse
import com.yangbong.data.remote.model.response.CheckDuplicateProfileNicknameResponse
import com.yangbong.data.remote.model.response.SignUpResponse

interface RemoteSetProfileDataSource {

    suspend fun checkDuplicateProfileNickname(profileNickname: String): NetworkState<BaseResponse<CheckDuplicateProfileNicknameResponse>>

    suspend fun postSignUp(signUpRequest: SignUpRequest): NetworkState<BaseResponse<SignUpResponse>>
}