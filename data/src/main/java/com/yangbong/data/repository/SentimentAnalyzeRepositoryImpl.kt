package com.yangbong.data.repository

import com.yangbong.core_data.exception.RetrofitFailureStateException
import com.yangbong.data.remote.call_adapter.NetworkState
import com.yangbong.data.remote.data_source.RemoteSentimentAnalyzeDataSource
import com.yangbong.data.remote.model.request.SentimentAnalyzeRequest
import com.yangbong.data.remote.model.response.SentimentAnalyzeResponse
import com.yangbong.domain.entity.request.DomainSentimentAnalyzeRequest
import com.yangbong.domain.entity.response.DomainSentimentAnalyzeResponse
import com.yangbong.domain.repository.SentimentAnalyzeRepository
import javax.inject.Inject

class SentimentAnalyzeRepositoryImpl @Inject constructor(
    private val sentimentAnalyzeDataSoure:RemoteSentimentAnalyzeDataSource
):SentimentAnalyzeRepository{

    override suspend fun postEmotion(SentimentRequest: DomainSentimentAnalyzeRequest):Result<DomainSentimentAnalyzeResponse> {
        val response=sentimentAnalyzeDataSoure.postSentimentAnalyze(
            SentimentAnalyzeRequest(
                content=SentimentRequest.content
            )
        )
        when(response){
            is NetworkState.Success->return Result.success(
                DomainSentimentAnalyzeResponse(
                    positive = response.body.document.confidence.positive,
                    negative = response.body.document.confidence.negative,
                    neutral = response.body.document.confidence.neutral,
                    sentiment = response.body.document.sentiment
                  )
            )
            is NetworkState.Failure->return Result.failure(
                RetrofitFailureStateException(
                    response.error,
                    response.code
                )
            )
        }
        return Result.failure(IllegalStateException("NetworkError or UnKnownError please check timber"))

    }
}