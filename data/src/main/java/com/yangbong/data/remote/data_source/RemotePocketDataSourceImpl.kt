package com.yangbong.data.remote.data_source

import com.yangbong.data.remote.call_adapter.NetworkState
import com.yangbong.data.remote.model.response.PocketResponse
import com.yangbong.data.remote.service.PocketService
import javax.inject.Inject

class RemotePocketDataSourceImpl @Inject constructor(
    private val pocketService: PocketService
):RemotePocketDataSource{
    override suspend fun getUserPocket(userId: Int): NetworkState<PocketResponse> {
        return pocketService.getUserPocket(userId)
    }

}
