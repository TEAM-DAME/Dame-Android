package com.yangbong.data.remote.data_source

import com.yangbong.data.remote.call_adapter.NetworkState
import com.yangbong.data.remote.model.response.BaseResponse
import com.yangbong.data.remote.model.response.UserProfileResponse
import com.yangbong.data.remote.service.UserProfileService
import javax.inject.Inject

class RemoteUserProfileDataSourceImpl @Inject constructor(
    private val userProfileService:UserProfileService
):RemoteUserProfileDataSource{
    override suspend fun getUserProfile(userId: Int): NetworkState<BaseResponse<UserProfileResponse>> {
        return userProfileService.getUserProfile(userId)
    }
}