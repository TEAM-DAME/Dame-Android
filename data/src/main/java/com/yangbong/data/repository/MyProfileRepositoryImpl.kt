package com.yangbong.data.repository

import com.yangbong.core_data.exception.RetrofitFailureStateException
import com.yangbong.data.local.data_source.LocalPreferenceUserDataSource
import com.yangbong.data.remote.call_adapter.NetworkState
import com.yangbong.data.remote.data_source.RemoteGetDiaryDataSource
import com.yangbong.data.remote.data_source.RemoteMyProfileDataSource
import com.yangbong.data.remote.mapper.DiaryMapper
import com.yangbong.domain.entity.DiaryInfo
import com.yangbong.domain.entity.MyProfileInfo
import com.yangbong.domain.repository.MyProfileRepository
import timber.log.Timber
import java.net.HttpURLConnection
import java.security.cert.CertificateException
import javax.inject.Inject

class MyProfileRepositoryImpl @Inject constructor(
    private val remoteMyProfileDataSource: RemoteMyProfileDataSource,
    private val remoteGetDiaryDataSource: RemoteGetDiaryDataSource,
    private val localPreferenceUserDataSource: LocalPreferenceUserDataSource,
    private val diaryMapper: DiaryMapper
) : MyProfileRepository {
    override suspend fun getMyProfileInfo(userId: Int): Result<MyProfileInfo> {
        when (val response = remoteMyProfileDataSource.getMyProfile(userId)) {
            is NetworkState.Success -> return Result.success(
                MyProfileInfo(
                    userId = response.body.data.userId,
                    nickName = response.body.data.nickName ?: "",
                    profileImageUrl = response.body.data.profileImageUrl ?: "",
                    diaryCount = response.body.data.diaryCount,
                    minionCount = response.body.data.minionCount,
                    friendCount = response.body.data.friendCount
                )
            )
            is NetworkState.Failure ->
                if (response.code == HttpURLConnection.HTTP_UNAUTHORIZED) throw CertificateException(
//                    DiaryRepositoryImpl.TOKEN_EXPIRED
                )
                else return Result.failure(
                    RetrofitFailureStateException(response.error, response.code)
                )

            is NetworkState.NetworkError -> Timber.d(response.error)
            is NetworkState.UnknownError -> Timber.d(response.t)
        }
//        return Result.failure(IllegalStateException(DiaryRepositoryImpl.UNKNOWN_ERROR))
        return Result.failure(IllegalStateException())
    }

    override suspend fun getDiaryList(userId: Int, page: Int, size: Int): Result<List<DiaryInfo>> {
        when (val diaryList = remoteGetDiaryDataSource.getDiaryList(userId, page, size)) {
            is NetworkState.Success -> return Result.success(
                diaryList.body.data.results.map {
                    diaryMapper.toDiaryInfo(it)
                }
            )
            is NetworkState.Failure -> {
                if (diaryList.code == 401) {
                    throw CertificateException("토큰 만료 오류")
                } else {
                    return Result.failure(
                        RetrofitFailureStateException(
                            diaryList.error,
                            diaryList.code
                        )
                    )
                }
            }
            is NetworkState.NetworkError -> Timber.tag("${this.javaClass.name}_getDiaryList")
                .d(diaryList.error)
            is NetworkState.UnknownError -> Timber.tag("${this.javaClass.name}_getDiaryList")
                .d(diaryList.t)
        }
        return Result.failure(IllegalStateException("NetworkError or UnKnownError please check timber"))
    }

    override fun getUserId(): Int {
        return localPreferenceUserDataSource.getUserId()
    }
}