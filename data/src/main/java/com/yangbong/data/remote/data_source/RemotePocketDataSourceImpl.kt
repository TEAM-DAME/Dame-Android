package com.yangbong.data.remote.data_source

import com.yangbong.data.remote.call_adapter.NetworkState
import com.yangbong.data.remote.model.response.BaseResponse
import com.yangbong.data.remote.model.response.Character
import com.yangbong.data.remote.service.PocketService
import javax.inject.Inject

class RemotePocketDataSourceImpl @Inject constructor(
    private val pocketService: PocketService
) : RemotePocketDataSource {
    override suspend fun getCharacterList(userId: Int): NetworkState<BaseResponse<List<Character>>> {
        return pocketService.getCharacterList(userId)
    }
}