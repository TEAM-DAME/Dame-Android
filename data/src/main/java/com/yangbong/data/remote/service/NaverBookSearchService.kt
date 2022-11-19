package com.yangbong.data.remote.service

import com.yangbong.data.remote.call_adapter.NetworkState
import com.yangbong.data.remote.model.request.NaverClovaSentimentRequest
import com.yangbong.data.remote.model.response.NaverClovaSentimentResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface NaverClovaSentimentService {

    @POST
    suspend fun postNaverClovaSentiment(
        @Body body: NaverClovaSentimentRequest
    ): NetworkState<NaverClovaSentimentResponse>
}