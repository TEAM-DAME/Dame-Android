package com.yangbong.data.remote.data_source

import com.yangbong.data.remote.call_adapter.NetworkState
import com.yangbong.data.remote.model.request.SetProfileRequest
import com.yangbong.data.remote.model.response.BaseResponse
import com.yangbong.data.remote.model.response.CheckDuplicateProfileIdResponse
import com.yangbong.data.remote.model.response.SetProfileResponse
import com.yangbong.data.remote.service.CheckDuplicateProfileIdService
import com.yangbong.data.remote.service.SetProfileService
import javax.inject.Inject

class RemoteSetProfileDataSourceImpl @Inject constructor(
    private val checkDuplicateProfileIdService: CheckDuplicateProfileIdService,
    private val setProfileService: SetProfileService
) : RemoteSetProfileDataSource {
    override suspend fun checkDuplicateProfileId(profileId: String): NetworkState<BaseResponse<CheckDuplicateProfileIdResponse>> =
        checkDuplicateProfileIdService.checkDuplicateProfileId(profileId)


    override suspend fun postUserProfile(setProfileRequest: SetProfileRequest): NetworkState<BaseResponse<SetProfileResponse>> =
        setProfileService.postSetProfile(setProfileRequest)

}