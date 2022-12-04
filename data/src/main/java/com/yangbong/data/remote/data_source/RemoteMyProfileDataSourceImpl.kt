package com.yangbong.data.remote.data_source

import com.yangbong.data.remote.call_adapter.NetworkState
import com.yangbong.data.remote.model.response.BaseResponse
import com.yangbong.data.remote.model.response.MyProfileResponse
import com.yangbong.data.remote.service.MyProfileService
import javax.inject.Inject

class RemoteMyProfileDataSourceImpl @Inject constructor(
    private val myProfileService: MyProfileService
) : RemoteMyProfileDataSource {
    override suspend fun getMyProfile(userId:Int): NetworkState<BaseResponse<MyProfileResponse>> =
        myProfileService.getMyProfile(userId)
}