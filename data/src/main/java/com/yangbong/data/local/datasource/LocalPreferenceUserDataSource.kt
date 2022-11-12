package com.yangbong.data.local.datasource

interface LocalPreferenceUserDataSource {
    fun getAccessToken(): String

    fun saveAccessToken(accessToken: String)

    fun getIsFirstVisited(): Boolean

    fun setIsFirstVisited(isFirstVisited: Boolean)

    fun saveUserNickname(userNickname: String)

    fun getUserNickname(): String

    fun saveUserProfileImageUrl(userProfileImageUrl: String)

    fun getUserProfileImageUrl(): String

    fun removeAccessToken()

    fun removeUserNickname()

    fun clearUserInfo()
}
