package com.yangbong.data.remote.data_source

import com.yangbong.data.remote.call_adapter.NetworkState
import com.yangbong.data.remote.model.request.SetProfileRequest
import com.yangbong.data.remote.model.response.BaseResponse
import com.yangbong.data.remote.model.response.CheckDuplicateProfileNicknameResponse
import com.yangbong.data.remote.model.response.EmptyResponse
import com.yangbong.data.remote.model.response.SetProfileResponse

interface RemoteSetProfileDataSource {

    suspend fun checkDuplicateProfileNickname(profileNickname: String): NetworkState<BaseResponse<CheckDuplicateProfileNicknameResponse>>

    suspend fun postUserProfile(setProfileRequest: SetProfileRequest): NetworkState<BaseResponse<SetProfileResponse>>
}