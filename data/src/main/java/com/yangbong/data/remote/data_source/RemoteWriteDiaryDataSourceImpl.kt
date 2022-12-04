package com.yangbong.data.remote.data_source

import com.yangbong.data.remote.call_adapter.NetworkState
import com.yangbong.data.remote.model.request.WriteDiaryRequest
import com.yangbong.data.remote.model.response.EmptyResponse
import com.yangbong.data.remote.service.WriteDiaryService
import javax.inject.Inject

class RemoteWriteDiaryDataSourceImpl @Inject constructor(
    private val writeDiaryService: WriteDiaryService
) : RemoteWriteDiaryDataSource {
    override suspend fun postDiary(diaryRequest: WriteDiaryRequest): NetworkState<EmptyResponse> {
        return writeDiaryService.postWriteDiary(diaryRequest)
    }
}