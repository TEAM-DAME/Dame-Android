package com.yangbong.data.repository

import com.yangbong.core_data.exception.RetrofitFailureStateException
import com.yangbong.data.local.data_source.LocalPreferenceUserDataSource
import com.yangbong.data.remote.call_adapter.NetworkState
import com.yangbong.data.remote.data_source.RemoteWriteDiaryDataSource
import com.yangbong.data.remote.data_source.RemoteSentimentAnalyzeDataSource
import com.yangbong.data.remote.model.request.EmotionRequest
import com.yangbong.data.remote.model.request.SentimentAnalyzeRequest
import com.yangbong.data.remote.model.request.WriteDiaryRequest
import com.yangbong.data.remote.model.response.Confidence
import com.yangbong.domain.entity.EmotionInfo
import com.yangbong.domain.entity.request.DomainDiaryRequest
import com.yangbong.domain.repository.WriteDiaryRepository
import timber.log.Timber
import java.lang.Double.max
import java.net.HttpURLConnection
import java.security.cert.CertificateException
import javax.inject.Inject
import kotlin.math.roundToInt

class WriteDiaryRepositoryImpl @Inject constructor(
    private val remoteSentimentAnalyzeDataSource: RemoteSentimentAnalyzeDataSource,
    private val remoteWriteDiaryDataSource: RemoteWriteDiaryDataSource,
    private val localPreferencesUserDataSource: LocalPreferenceUserDataSource
) : WriteDiaryRepository {
    override suspend fun postSentiment(content: String): Result<EmotionInfo> {
        val emotionResult = remoteSentimentAnalyzeDataSource.postSentimentAnalyze(
            SentimentAnalyzeRequest(
                content = content
            )
        )
        when (emotionResult) {
            is NetworkState.Success -> return Result.success(
                EmotionInfo(
                    positive = emotionResult.body.document.confidence.positive,
                    neutral = emotionResult.body.document.confidence.neutral,
                    negative = emotionResult.body.document.confidence.negative
                )
            )
            is NetworkState.Failure -> return Result.failure(
                RetrofitFailureStateException(
                    emotionResult.error,
                    emotionResult.code
                )
            )
            is NetworkState.NetworkError -> Timber.tag("${this.javaClass.name}_postSentiment")
                .d(emotionResult.error)
            is NetworkState.UnknownError -> Timber.tag("${this.javaClass.name}_postSentiment")
                .d(emotionResult.t)
        }
        return Result.failure(IllegalStateException("NetworkError or UnKnownError please check timber"))
    }

    override suspend fun postDiary(diaryRequest: DomainDiaryRequest): Result<String> {
        val response = remoteWriteDiaryDataSource.postDiary(
            WriteDiaryRequest(
                minionId = diaryRequest.minionId,
                userId = diaryRequest.userId,
                title = diaryRequest.title,
                content = diaryRequest.content,
                emotion = EmotionRequest(
                    positive = diaryRequest.positive,
                    neutral = diaryRequest.neutral,
                    negative = diaryRequest.negative
                )
            )
        )
        when (response) {
            is NetworkState.Success -> return Result.success(response.body.message)
            is NetworkState.Failure ->
                if (response.code == HttpURLConnection.HTTP_UNAUTHORIZED) throw CertificateException(
                    TOKEN_EXPIRED
                ) else return Result.failure(
                    RetrofitFailureStateException(response.error, response.code)
                )
            is NetworkState.NetworkError -> Timber.d(response.error)
            is NetworkState.UnknownError -> Timber.d(response.t)
        }
        return Result.failure(IllegalStateException(UNKNOWN_ERROR))
    }

    override fun getUserId(): Int {
        return localPreferencesUserDataSource.getUserId()
    }

    companion object {
        const val TOKEN_EXPIRED = "토큰 만료 오류"
        const val UNKNOWN_ERROR = "NetworkError or UnKnownError please check timber"
    }

    private fun Confidence.maxValue(): Int {
        val maxValue = max(max(this.positive, this.neutral), this.negative)
        return maxValue.roundToInt()
    }
}