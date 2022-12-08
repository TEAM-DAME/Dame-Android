package com.yangbong.data.repository

import com.yangbong.core_data.exception.RetrofitFailureStateException
import com.yangbong.data.remote.call_adapter.NetworkState
import com.yangbong.data.remote.data_source.RemoteDiaryDataSource
import com.yangbong.domain.entity.Diary
import com.yangbong.domain.repository.DiaryRepository
import timber.log.Timber
import java.security.cert.CertificateException
import javax.inject.Inject

class DiaryRepositoryImpl @Inject constructor(
    private val remoteDiaryDataSource: RemoteDiaryDataSource
) : DiaryRepository {
    override suspend fun getDiaryContent(userId: Int, diaryId: Int): Result<Diary> {
        when (val diaryContent = remoteDiaryDataSource.getDiaryContent(userId, diaryId)) {
            is NetworkState.Success -> return Result.success(
                Diary(
                    minionId = diaryContent.body.data.minionId,
                    title = diaryContent.body.data.title,
                    content = diaryContent.body.data.content,
                    createdAt = diaryContent.body.data.createdAt
                )
            )
            is NetworkState.Failure -> {
                if (diaryContent.code == 401) {
                    throw CertificateException("토큰 만료 오류")
                } else {
                    return Result.failure(
                        RetrofitFailureStateException(
                            diaryContent.error,
                            diaryContent.code
                        )
                    )
                }
            }
            is NetworkState.NetworkError -> Timber.tag("${this.javaClass.name}_getDiaryContent")
                .d(diaryContent.error)
            is NetworkState.UnknownError -> Timber.tag("${this.javaClass.name}_getDiaryContent")
                .d(diaryContent.t)
        }
        return Result.failure(IllegalStateException("NetworkError or UnKnownError please check timber"))
    }
}