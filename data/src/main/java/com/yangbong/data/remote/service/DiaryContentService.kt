package com.yangbong.data.remote.service

import com.yangbong.data.remote.call_adapter.NetworkState
import com.yangbong.data.remote.model.response.BaseResponse
import com.yangbong.data.remote.model.response.DiaryResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface DiaryContentService {

    @GET("v1/diary/{userId}/{diaryId}")
    suspend fun getDiaryContent(
        @Path("userId") userId: Int,
        @Path("diaryId") diaryId: Int
    ): NetworkState<BaseResponse<DiaryResponse>>
}