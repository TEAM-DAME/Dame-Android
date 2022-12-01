package com.yangbong.domain.repository

import com.yangbong.domain.entity.request.DomainSentimentAnalyzeRequest
import com.yangbong.domain.entity.response.DomainSentimentAnalyzeResponse

interface SentimentAnalyzeRepository {
    suspend fun postEmotion(SentimentRequest:DomainSentimentAnalyzeRequest):Result<DomainSentimentAnalyzeResponse>
}