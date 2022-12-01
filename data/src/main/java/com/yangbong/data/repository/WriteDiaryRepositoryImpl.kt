package com.yangbong.data.repository


import com.yangbong.data.remote.call_adapter.NetworkState
import com.yangbong.data.local.data_source.LocalPreferenceUserDataSource
import com.yangbong.data.remote.data_source.RemoteWriteDiarySource
import com.yangbong.data.remote.model.request.Emotion
import com.yangbong.data.remote.model.request.WriteDiaryRequest
import com.yangbong.domain.entity.request.DomainWriteDiaryRequest
import com.yangbong.domain.repository.WriteDiaryRepository
import javax.inject.Inject

class WriteDiaryRepositoryImpl @Inject constructor(
    private val writeDiaryDataSource:RemoteWriteDiarySource,
    private val localPreferenceUserDataSource: LocalPreferenceUserDataSource
) :WriteDiaryRepository{
    override fun getUserId(): Int {
        return localPreferenceUserDataSource.getUserId()
    }

    override suspend fun postWriteDiary(WriteRequest: DomainWriteDiaryRequest): Result<String> {
        when(val response=writeDiaryDataSource.postWriteDiary(
            WriteDiaryRequest(
                userId=WriteRequest.userId,
                title=WriteRequest.title,
                content=WriteRequest.content,
                emotion = Emotion(WriteRequest.positive,WriteRequest.neutral,WriteRequest.negative)

            )
        )){
            is NetworkState.Success -> return Result.success(
                response.body.message
            )
            is NetworkState.Failure->return Result.failure(
                IllegalStateException(
                    "NetworkError or UnKnownError please check "
                )
            )
        }
        return Result.failure(
            IllegalStateException(
                "NetworkError or UnKnownError please check "
            )
        )
    }
}