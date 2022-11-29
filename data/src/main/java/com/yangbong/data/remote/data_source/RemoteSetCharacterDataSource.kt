package com.yangbong.data.remote.data_source

import com.yangbong.data.remote.call_adapter.NetworkState
import com.yangbong.data.remote.model.request.SetCharacterRequest
import com.yangbong.data.remote.model.response.EmptyResponse

interface RemoteSetCharacterDataSource {

    suspend fun postCharacter(setCharacterRequest: SetCharacterRequest): NetworkState<EmptyResponse>
}