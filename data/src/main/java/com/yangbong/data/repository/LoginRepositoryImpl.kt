package com.yangbong.data.repository

import com.yangbong.core_data.exception.RetrofitFailureStateException
import com.yangbong.core_data.fcm.FirebaseTokenManager
import com.yangbong.data.local.data_source.LocalPreferenceUserDataSource
import com.yangbong.data.remote.call_adapter.NetworkState
import com.yangbong.data.remote.data_source.RemoteLoginDataSource
import com.yangbong.data.remote.model.request.LoginRequest
import com.yangbong.domain.entity.request.DomainLoginRequest
import com.yangbong.domain.entity.response.DomainLoginResponse
import com.yangbong.domain.repository.LoginRepository
import timber.log.Timber
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(
    private val localPreferenceUserDataSource: LocalPreferenceUserDataSource,
    private val remoteLoginDataSource: RemoteLoginDataSource,
    private val firebaseTokenManager: FirebaseTokenManager,
) : LoginRepository {
    override fun getFcmToken(tokenCallBack: (String) -> Unit) {
        firebaseTokenManager.getFirebaseToken {
            tokenCallBack(it)
        }
    }

    override fun getAccessToken(): String {
        return localPreferenceUserDataSource.getAccessToken()
    }

    override fun saveAccessToken(accessToken: String) {
        localPreferenceUserDataSource.saveAccessToken(accessToken)
    }

    override fun getIsFirstVisited(): Boolean = localPreferenceUserDataSource.getIsFirstVisited()

    override fun setIsFirstVisited(isFirstVisited: Boolean) {
        localPreferenceUserDataSource.setIsFirstVisited(isFirstVisited)
    }

    override fun saveUserProfileId(userProfileId: String) {
        localPreferenceUserDataSource.saveUserProfileId(userProfileId)
    }

    override fun saveUserProfileImageUrl(userProfileImageUrl: String) {
        localPreferenceUserDataSource.saveUserProfileImageUrl(userProfileImageUrl)
    }

    override suspend fun postLogin(loginRequest: DomainLoginRequest): Result<DomainLoginResponse> {
        val response = remoteLoginDataSource.postLogin(
            LoginRequest(
                socialToken = loginRequest.socialToken,
                fcmToken = loginRequest.fcmToken
            )
        )

        when (response) {
            is NetworkState.Success -> return Result.success(
                DomainLoginResponse(
                    accessToken = response.body.data.accessToken,
                    isNewUser = response.body.data.isNewUser
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
                "${this.javaClass.name}_postKakaoLogin"
            )
            is NetworkState.UnknownError -> Timber.d(
                response.t,
                "${this.javaClass.name}_postKakaoLogin"
            )
        }
        return Result.failure(IllegalStateException("NetworkError or UnKnownError please check timber"))
    }

}
