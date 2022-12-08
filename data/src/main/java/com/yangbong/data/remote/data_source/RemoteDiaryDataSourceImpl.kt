package com.yangbong.data.remote.data_source

import com.yangbong.data.remote.call_adapter.NetworkState
import com.yangbong.data.remote.model.response.BaseResponse
import com.yangbong.data.remote.model.response.DiaryResponse
import com.yangbong.data.remote.service.DiaryContentService
import javax.inject.Inject

class RemoteDiaryDataSourceImpl @Inject constructor(
    private val diaryContentService: DiaryContentService
) : RemoteDiaryDataSource {
    override suspend fun getDiaryContent(
        userId: Int,
        diaryId: Int
    ): NetworkState<BaseResponse<DiaryResponse>> {
        return diaryContentService.getDiaryContent(userId, diaryId)
    }
}