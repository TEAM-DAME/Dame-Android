package com.yangbong.data.remote.data_source

import com.yangbong.data.remote.call_adapter.NetworkState
import com.yangbong.data.remote.model.request.SignUpRequest
import com.yangbong.data.remote.model.response.BaseResponse
import com.yangbong.data.remote.model.response.CheckDuplicateProfileNicknameResponse
import com.yangbong.data.remote.model.response.SignUpResponse
import com.yangbong.data.remote.service.CheckDuplicateProfileNicknameService
import com.yangbong.data.remote.service.SignUpService
import javax.inject.Inject

class RemoteSetProfileDataSourceImpl @Inject constructor(
    private val checkDuplicateProfileNicknameService: CheckDuplicateProfileNicknameService,
    private val signUpService: SignUpService
) : RemoteSetProfileDataSource {
    override suspend fun checkDuplicateProfileNickname(profileNickname: String): NetworkState<BaseResponse<CheckDuplicateProfileNicknameResponse>> =
        checkDuplicateProfileNicknameService.checkDuplicateProfileNickname(profileNickname)


    override suspend fun postSignUp(signUpRequest: SignUpRequest): NetworkState<BaseResponse<SignUpResponse?>> =
        signUpService.postSignUp(signUpRequest)
}