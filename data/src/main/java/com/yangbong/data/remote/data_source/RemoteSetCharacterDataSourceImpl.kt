package com.yangbong.data.remote.data_source

import com.yangbong.data.remote.call_adapter.NetworkState
import com.yangbong.data.remote.model.request.SetCharacterRequest
import com.yangbong.data.remote.model.response.EmptyResponse
import com.yangbong.data.remote.service.SetCharacterService
import javax.inject.Inject


class RemoteSetCharacterDataSourceImpl @Inject constructor(
    private val setCharacterService: SetCharacterService
):RemoteSetCharacterDataSource {
    override suspend fun postCharacter(setCharacterRequest: SetCharacterRequest): NetworkState<EmptyResponse> {
        return setCharacterService.postCharacter(setCharacterRequest)
    }
}