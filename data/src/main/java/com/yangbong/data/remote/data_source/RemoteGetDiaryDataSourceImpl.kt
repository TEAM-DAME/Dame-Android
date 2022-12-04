package com.yangbong.data.remote.data_source

import com.yangbong.data.remote.call_adapter.NetworkState
import com.yangbong.data.remote.model.response.BaseResponse
import com.yangbong.data.remote.model.response.DiaryListResponse
import com.yangbong.data.remote.service.GetDiaryService
import javax.inject.Inject

class RemoteGetDiaryDataSourceImpl @Inject constructor(
    private val getDiaryService: GetDiaryService
) : RemoteGetDiaryDataSource {
    override suspend fun getDiaryList(
        userId: Int,
        page: Int,
        size: Int
    ): NetworkState<BaseResponse<DiaryListResponse>> {
        return getDiaryService.getDiaryList(userId, page, size)
    }
}