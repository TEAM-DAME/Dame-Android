package com.yangbong.data.repository

import com.yangbong.data.local.datasource.LocalPreferenceUserDataSource
import com.yangbong.domain.repository.LoginRepository
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(
    private val localPreferenceUserDataSource: LocalPreferenceUserDataSource
) : LoginRepository {
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

    override fun saveUserNickname(userNickname: String) {
        localPreferenceUserDataSource.saveUserNickname(userNickname)
    }

    override fun saveUserProfileImageUrl(userProfileImageUrl: String) {
        localPreferenceUserDataSource.saveUserProfileImageUrl(userProfileImageUrl)
    }

}
