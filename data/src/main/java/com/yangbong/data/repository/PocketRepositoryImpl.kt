package com.yangbong.data.repository

import com.yangbong.core_data.exception.RetrofitFailureStateException
import com.yangbong.data.local.data_source.LocalPreferenceUserDataSource
import com.yangbong.data.remote.call_adapter.NetworkState
import com.yangbong.data.remote.data_source.RemotePocketDataSource
import com.yangbong.domain.entity.CharacterInfo
import com.yangbong.domain.entity.response.DomainPocketResponse
import com.yangbong.domain.repository.PocketRepository
import retrofit2.Retrofit
import timber.log.Timber
import javax.inject.Inject

class PocketRepositoryImpl @Inject constructor(
    private val remotePocketDataSource: RemotePocketDataSource,
    private val localPreferenceUserDataSource: LocalPreferenceUserDataSource
) : PocketRepository{
    override suspend fun getUserPocket(userId: Int): Result<DomainPocketResponse> {
        val pocketResult=remotePocketDataSource.getUserPocket(userId)
        when(pocketResult){
            is NetworkState.Success -> return Result.success(
                DomainPocketResponse(
                      data  =pocketResult.body.data
                )
            )
            is NetworkState.Failure->
                return Result.failure(
                    RetrofitFailureStateException(
                        pocketResult.error,
                        pocketResult.code
                    )
                )
            is NetworkState.NetworkError -> Timber.d(pocketResult.error)
            is NetworkState.UnknownError -> Timber.d(pocketResult.t)
        }
        return Result.failure(IllegalStateException("NetworkError or UnKnownError please check timber"))

    }
    override fun getUserId(): Int {
        return localPreferenceUserDataSource.getUserId()
    }

    override fun getUserNickName(): String {
        return localPreferenceUserDataSource.getUserProfileNickname()
    }

}