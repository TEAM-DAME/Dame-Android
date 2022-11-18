package com.yangbong.data.repository

import com.yangbong.core_data.exception.RetrofitFailureStateException
import com.yangbong.data.local.data_source.LocalPreferenceUserDataSource
import com.yangbong.data.remote.call_adapter.NetworkState
import com.yangbong.data.remote.data_source.RemoteSetProfileDataSource
import com.yangbong.data.remote.model.request.SetProfileRequest
import com.yangbong.domain.entity.UserInfo
import com.yangbong.domain.entity.request.DomainSetProfileRequest
import com.yangbong.domain.entity.response.DomainSetProfileResponse
import com.yangbong.domain.repository.SetProfileRepository
import timber.log.Timber
import javax.inject.Inject

class SetProfileRepositoryImpl @Inject constructor(
    private val setProfileDataSource: RemoteSetProfileDataSource,
    private val localPreferenceUserDataSource: LocalPreferenceUserDataSource
) : SetProfileRepository {
    override suspend fun checkDuplicateProfileId(profileId: String): Result<Boolean> {

        when (val response = setProfileDataSource.checkDuplicateProfileId(profileId)) {
            is NetworkState.Success -> return Result.success(
                response.body.data.available ?: throw IllegalStateException()
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

    override suspend fun postUserProfile(setProfileRequest: DomainSetProfileRequest): Result<DomainSetProfileResponse> {
        val response = setProfileDataSource.postUserProfile(
            SetProfileRequest(
                profileId = setProfileRequest.profileId,
                profileImgUrl = setProfileRequest.profileImgUrl
            )
        )

        when (response) {
            is NetworkState.Success -> return Result.success(
                DomainSetProfileResponse(
                    accessToken = response.body.data.accessToken,
                    userInfo = UserInfo(
                        profileId = response.body.data.user.profileId,
                        profileImageUrl = response.body.data.user.profileImageUrl
                    )
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

    override fun saveUserProfileId(profileId: String) {
        localPreferenceUserDataSource.saveUserProfileId(profileId)
    }

    override fun saveUserProfileImageUrl(profileImageUrl: String) {
        localPreferenceUserDataSource.saveUserProfileImageUrl(profileImageUrl)
    }

    override fun getUserProfileImageUrl(): String {
        return localPreferenceUserDataSource.getUserProfileImageUrl()
    }

}