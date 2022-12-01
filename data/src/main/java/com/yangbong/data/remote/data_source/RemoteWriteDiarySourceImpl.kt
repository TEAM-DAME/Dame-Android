package com.yangbong.data.remote.data_source

import com.yangbong.data.remote.call_adapter.NetworkState
import com.yangbong.data.remote.model.request.WriteDiaryRequest
import com.yangbong.data.remote.model.response.EmptyResponse
import com.yangbong.data.remote.service.WriteDiaryService
import javax.inject.Inject

class RemoteWriteDiarySourceImpl @Inject constructor(
    private val writeDiaryService: WriteDiaryService
):RemoteWriteDiarySource{
    override suspend fun postWriteDiary(writeDiaryRequest: WriteDiaryRequest): NetworkState<EmptyResponse> {
        return writeDiaryService.postWriteDiary(writeDiaryRequest)
    }
}