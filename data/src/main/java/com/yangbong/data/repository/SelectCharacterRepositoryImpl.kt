package com.yangbong.data.repository

import com.yangbong.core_data.exception.RetrofitFailureStateException
import com.yangbong.data.local.data_source.LocalPreferenceUserDataSource
import com.yangbong.data.remote.call_adapter.NetworkState
import com.yangbong.data.remote.data_source.RemoteSelectCharacterDataSource
import com.yangbong.data.remote.model.request.SelectCharacterRequest
import com.yangbong.domain.entity.request.DomainSelectCharacterRequest
import com.yangbong.domain.entity.response.DomainSelectCharacterResponse
import com.yangbong.domain.repository.SelectCharacterRepository
import timber.log.Timber
import javax.inject.Inject

class SelectCharacterRepositoryImpl @Inject constructor(
    private val selectCharacterRemoteDataSource: RemoteSelectCharacterDataSource,
    private val localPreferenceUserDataSource: LocalPreferenceUserDataSource
) : SelectCharacterRepository {
    override suspend fun putInitCharacter(selectCharacterRequest: DomainSelectCharacterRequest): Result<DomainSelectCharacterResponse> {
        val response = selectCharacterRemoteDataSource.putInitCharacter(
            SelectCharacterRequest(
                userid = selectCharacterRequest.userid,
                minion = selectCharacterRequest.minion
            )
        )

        when (response){
            is NetworkState.Success -> return Result.success(
                DomainSelectCharacterResponse(
                    message = response.body.message
                )
            )

            is NetworkState.Failure -> return Result.failure(
                RetrofitFailureStateException(
                    response.error,
                    response.code
                )
            )

            is NetworkState.NetworkError -> Timber.tag("${this.javaClass.name}_postKakaoLogin")
                .d(response.error)
            is NetworkState.UnknownError -> Timber.tag("${this.javaClass.name}_postKakaoLogin")
                .d(response.t)
        }
        return Result.failure(IllegalStateException("NetworkError or UnKnownError please check timber"))
    }

    override fun saveUserId(userid: Int) {
        localPreferenceUserDataSource.saveUserId(userid)
    }

    override fun saveInitCharacter(minion: Int) {
        localPreferenceUserDataSource.saveInitCharacter(minion)
    }

}