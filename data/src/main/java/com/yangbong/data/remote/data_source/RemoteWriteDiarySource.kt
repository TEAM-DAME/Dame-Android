package com.yangbong.data.remote.data_source

import com.yangbong.data.remote.call_adapter.NetworkState
import com.yangbong.data.remote.model.request.WriteDiaryRequest
import com.yangbong.data.remote.model.response.EmptyResponse

interface RemoteWriteDiarySource {
    suspend fun postWriteDiary(writeDiaryRequest: WriteDiaryRequest):NetworkState<EmptyResponse>
}