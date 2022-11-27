package com.yangbong.data.repository

import com.yangbong.core_data.exception.RetrofitFailureStateException
import com.yangbong.data.local.data_source.LocalPreferenceUserDataSource
import com.yangbong.data.remote.call_adapter.NetworkState
import com.yangbong.data.remote.data_source.RemoteSetProfileDataSource
import com.yangbong.data.remote.model.request.SignUpRequest
import com.yangbong.domain.entity.request.DomainSignUpRequest
import com.yangbong.domain.entity.response.DomainSignUpResponse
import com.yangbong.domain.repository.SetProfileRepository
import timber.log.Timber
import javax.inject.Inject

class SetProfileRepositoryImpl @Inject constructor(
    private val setProfileDataSource: RemoteSetProfileDataSource,
    private val localPreferenceUserDataSource: LocalPreferenceUserDataSource
) : SetProfileRepository {
    override suspend fun checkDuplicateProfileNickname(profileNickname: String): Result<Boolean> {

        when (val response = setProfileDataSource.checkDuplicateProfileNickname(profileNickname)) {
            is NetworkState.Success -> return Result.success(
                response.body.data.available
            )
            is NetworkState.Failure -> return Result.failure(
                RetrofitFailureStateException(
                    response.error,
                    response.code
                )
            )
            is NetworkState.NetworkError -> Timber.tag("${this.javaClass.name}_checkDuplicateNickname")
                .d(response.error)
            is NetworkState.UnknownError -> Timber.tag("${this.javaClass.name}_checkDuplicateNickname")
                .d(response.t)
        }
        return Result.failure(IllegalStateException("NetworkError or UnKnownError please check timber"))
    }

    override suspend fun postSignUp(signUpRequest: DomainSignUpRequest): Result<DomainSignUpResponse> {
        val response = setProfileDataSource.postSignUp(
            SignUpRequest(
                platform = signUpRequest.platform,
                socialToken = signUpRequest.socialToken,
                fcmToken = signUpRequest.fcmToken,
                nickname = signUpRequest.nickname,
                profileImageUrl = signUpRequest.profileImageUrl
            )
        )

        when (response) {
            is NetworkState.Success -> return Result.success(
                DomainSignUpResponse(
                    userId = response.body.data.userId
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

    override fun saveUserId(userId: Int) {
        localPreferenceUserDataSource.saveUserId(userId)
    }

    override fun saveUserProfileNickname(profileNickname: String) {
        localPreferenceUserDataSource.saveUserProfileNickname(profileNickname)
    }

    override fun saveUserProfileImageUrl(profileImageUrl: String) {
        localPreferenceUserDataSource.saveUserProfileImageUrl(profileImageUrl)
    }

    override fun getUserProfileImageUrl(): String {
        return localPreferenceUserDataSource.getUserProfileImageUrl()
    }

}