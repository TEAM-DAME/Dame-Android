package com.yangbong.data.repository

import com.yangbong.core_data.exception.RetrofitFailureStateException
import com.yangbong.data.remote.call_adapter.NetworkState
import com.yangbong.data.remote.data_source.RemoteMyProfileDataSource
import com.yangbong.data.remote.mapper.DiaryMapper
import com.yangbong.domain.entity.MyProfileInfo
import com.yangbong.domain.entity.MyProfileUser
import com.yangbong.domain.repository.MyProfileRepository
import timber.log.Timber
import java.net.HttpURLConnection
import java.security.cert.CertificateException
import javax.inject.Inject

class MyProfileRepositoryImpl @Inject constructor(
    private val remoteUserDataSource: RemoteMyProfileDataSource,
    private val diaryMapper: DiaryMapper
) : MyProfileRepository {
    override suspend fun getMyProfileInfo(): Result<MyProfileInfo> {
        when (val response = remoteUserDataSource.getMyProfile()) {
            is NetworkState.Success -> return Result.success(
                MyProfileInfo(
                    myProfileUser = MyProfileUser(
                        response.body.data.profileId,
                        response.body.data.profileImageUrl,
                        response.body.data.diaryCount,
                        response.body.data.pocketCount,
                        response.body.data.friendCount
                    ),
                    diaryList = response.body.data.diaries.map {
                        diaryMapper.toDiaryInfo(it)
                    }
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
}