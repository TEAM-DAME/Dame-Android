package com.yangbong.data.remote.data_source

import com.yangbong.data.remote.call_adapter.NetworkState
import com.yangbong.data.remote.model.request.SelectCharacterRequest
import com.yangbong.data.remote.model.request.SetProfileRequest
import com.yangbong.data.remote.model.response.BaseResponse
import com.yangbong.data.remote.model.response.CheckDuplicateProfileIdResponse
import com.yangbong.data.remote.model.response.SelectCharacterResponse
import com.yangbong.data.remote.model.response.SetProfileResponse
import com.yangbong.data.remote.service.CheckDuplicateProfileIdService
import com.yangbong.data.remote.service.SelectCharacterService
import com.yangbong.data.remote.service.SetProfileService
import com.yangbong.domain.entity.request.DomainSelectCharacterRequest
import javax.inject.Inject

class RemoteSelectCharacterDataSourceImpl @Inject constructor(
    private val selectCharacterService: SelectCharacterService
) : RemoteSelectCharacterDataSource {

    override suspend fun putInitCharacter(selectCharacterRequest: SelectCharacterRequest): NetworkState<BaseResponse<SelectCharacterResponse>> =
        selectCharacterService.putInitCharacter(selectCharacterRequest)

}