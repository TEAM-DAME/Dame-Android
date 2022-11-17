package com.yangbong.data.remote.data_source

import com.yangbong.data.remote.call_adapter.NetworkState
import com.yangbong.data.remote.model.request.SelectCharacterRequest
import com.yangbong.data.remote.model.request.SetProfileRequest
import com.yangbong.data.remote.model.response.BaseResponse
import com.yangbong.data.remote.model.response.SelectCharacterResponse
import com.yangbong.data.remote.model.response.SetProfileResponse
import com.yangbong.domain.entity.request.DomainSelectCharacterRequest

interface RemoteSelectCharacterDataSource {

    suspend fun putInitCharacter(selectCharacterRequest: SelectCharacterRequest): NetworkState<BaseResponse<SelectCharacterResponse>>

}