package com.yangbong.data.remote.data_source

import com.yangbong.data.remote.call_adapter.NetworkState
import com.yangbong.data.remote.model.request.SentimentAnalyzeRequest
import com.yangbong.data.remote.model.response.BaseResponse
import com.yangbong.data.remote.model.response.SentimentAnalyzeResponse

interface RemoteSentimentAnalyzeDataSource {

    suspend fun postSentimentAnalyze(
        sentimentAnalyzeRequest: SentimentAnalyzeRequest
    ): NetworkState<SentimentAnalyzeResponse>

    suspend fun getRecentReadList(): NetworkState<BaseResponse<SentimentAnalyzeResponse>>
}