package com.yangbong.data.repository

import com.yangbong.core_data.exception.RetrofitFailureStateException
import com.yangbong.data.remote.call_adapter.NetworkState
import com.yangbong.data.remote.data_source.RemoteCharacterDataSource
import com.yangbong.domain.entity.response.DomainCharacterResponse
import com.yangbong.domain.repository.HomeRepository
import timber.log.Timber
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(
    private val remoteCharacterDataSource: RemoteCharacterDataSource,
) : HomeRepository {

    override suspend fun getCharacterInfo(userId: Int): Result<DomainCharacterResponse> {

        when (val response = remoteCharacterDataSource.getCharacterInfo(userId)) {
            is NetworkState.Success -> return Result.success(
                DomainCharacterResponse(
                    characterId = response.body.data.characterId,
                    characterExp = response.body.data.characterExp,
                    isNotification = response.body.data.isNotification
                )
            )
            is NetworkState.Failure -> return Result.failure(
                RetrofitFailureStateException(
                    response.error,
                    response.code
                )
            )
            is NetworkState.NetworkError -> Timber.d(
                response.error,
                "${this.javaClass.name}_getCharacterInfo"
            )
            is NetworkState.UnknownError -> Timber.d(
                response.t,
                "${this.javaClass.name}_getCharacterInfo"
            )
        }
        return Result.failure(IllegalStateException("NetworkError or UnKnownError please check timber"))
    }
}