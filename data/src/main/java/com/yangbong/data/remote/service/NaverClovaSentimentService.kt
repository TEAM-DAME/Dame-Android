package com.yangbong.data.remote.service

import com.yangbong.data.remote.call_adapter.NetworkState
import com.yangbong.data.remote.model.request.SentimentAnalyzeRequest
import com.yangbong.data.remote.model.response.SentimentAnalyzeResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface NaverClovaSentimentService {

    @POST("v1/analyze")
    suspend fun postSentimentAnalyze(
        @Body body: SentimentAnalyzeRequest
    ): NetworkState<SentimentAnalyzeResponse>
}