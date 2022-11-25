package com.yangbong.data.remote.service

import com.yangbong.data.remote.call_adapter.NetworkState
import com.yangbong.data.remote.model.request.WriteDiaryRequest
import com.yangbong.data.remote.model.response.BaseResponse
import com.yangbong.data.remote.model.response.WriteDiaryResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface WriteDiaryService {
    @POST("v1/diary")
    suspend fun postWriteDiary(
        @Body body:WriteDiaryRequest
    ):NetworkState<BaseResponse<WriteDiaryResponse>>
}