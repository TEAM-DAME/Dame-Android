package com.yangbong.data.repository

import com.yangbong.core_data.exception.RetrofitFailureStateException
import com.yangbong.data.remote.call_adapter.NetworkState
import com.yangbong.data.remote.data_source.RemotePocketDataSource
import com.yangbong.data.remote.mapper.CharacterMapper
import com.yangbong.domain.entity.CharacterInfo
import com.yangbong.domain.repository.PocketRepository
import timber.log.Timber
import java.security.cert.CertificateException
import javax.inject.Inject

class PocketRepositoryImpl @Inject constructor(
    private val remotePocketDataSource: RemotePocketDataSource,
    private val characterMapper: CharacterMapper
) : PocketRepository {
    override suspend fun getCharacterList(userId: Int): Result<List<CharacterInfo>> {
        when (val characterList = remotePocketDataSource.getCharacterList(userId)) {
            is NetworkState.Success -> return Result.success(
                characterList.body.data.map {
                    characterMapper.toCharacterInfo(it)
                }
            )
            is NetworkState.Failure -> {
                if (characterList.code == 401) {
                    throw CertificateException("토큰 만료 오류")
                } else {
                    return Result.failure(
                        RetrofitFailureStateException(
                            characterList.error,
                            characterList.code
                        )
                    )
                }
            }
            is NetworkState.NetworkError -> Timber.tag("${this.javaClass.name}_getCharacterList")
                .d(characterList.error)
            is NetworkState.UnknownError -> Timber.tag("${this.javaClass.name}_getCharacterList")
                .d(characterList.t)
        }
        return Result.failure(IllegalStateException("NetworkError or UnKnownError please check timber"))
    }
}