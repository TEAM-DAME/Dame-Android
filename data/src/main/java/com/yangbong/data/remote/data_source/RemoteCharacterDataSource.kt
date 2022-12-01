package com.yangbong.data.remote.data_source

import com.yangbong.data.remote.call_adapter.NetworkState
import com.yangbong.data.remote.model.response.BaseResponse
import com.yangbong.data.remote.model.response.CharacterResponse

interface RemoteCharacterDataSource {

    suspend fun getCharacterInfo(userId: Int): NetworkState<BaseResponse<CharacterResponse>>
}