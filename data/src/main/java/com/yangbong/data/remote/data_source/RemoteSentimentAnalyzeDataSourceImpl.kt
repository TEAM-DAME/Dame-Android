package com.yangbong.data.remote.data_source

import com.yangbong.data.remote.call_adapter.NetworkState
import com.yangbong.data.remote.model.request.SentimentAnalyzeRequest
import com.yangbong.data.remote.model.response.BaseResponse
import com.yangbong.data.remote.model.response.SentimentAnalyzeResponse
import com.yangbong.data.remote.service.NaverClovaSentimentService
import javax.inject.Inject

class RemoteSentimentAnalyzeDataSourceImpl @Inject constructor(
    private val sentimentAnalyzeService:NaverClovaSentimentService
):RemoteSentimentAnalyzeDataSource{
    override suspend fun postSentimentAnalyze(sentimentAnalyzeRequest: SentimentAnalyzeRequest): NetworkState<SentimentAnalyzeResponse> {
        return sentimentAnalyzeService.postSentimentAnalyze(sentimentAnalyzeRequest)
    }

//    override suspend fun getRecentReadList(): NetworkState<BaseResponse<SentimentAnalyzeResponse>> {
//
//    }
}