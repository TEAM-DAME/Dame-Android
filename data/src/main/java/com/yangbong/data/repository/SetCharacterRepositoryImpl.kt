package com.yangbong.data.repository

import com.yangbong.data.remote.call_adapter.NetworkState
import com.yangbong.data.remote.data_source.RemoteSetCharacterDataSource
import com.yangbong.data.remote.model.request.SetCharacterRequest
import com.yangbong.domain.entity.request.DomainCharacterRequest
import com.yangbong.domain.repository.SetCharacterRepository
import javax.inject.Inject

class SetCharacterRepositoryImpl @Inject constructor(
    private val setCharacterDataSource: RemoteSetCharacterDataSource
) : SetCharacterRepository {

    override suspend fun postCharacter(characterRequest: DomainCharacterRequest): Result<String> {
        when (val response = setCharacterDataSource.postCharacter(
            SetCharacterRequest(
                userId = characterRequest.userId,
                minion = characterRequest.minion
            )
        )) {
            is NetworkState.Success -> return Result.success(
                response.body.message
            )
            is NetworkState.Failure -> return Result.failure(
                IllegalStateException(
                    "NetworkError or UnKnownError please check "
                )
            )
            else -> Unit
        }
        return Result.failure(
            IllegalStateException(
                "NetworkError or UnKnownError please check "
            )
        )
    }

}