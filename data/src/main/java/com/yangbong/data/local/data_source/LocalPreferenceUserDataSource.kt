package com.yangbong.data.local.data_source

interface LocalPreferenceUserDataSource {
    fun getAccessToken(): String

    fun saveAccessToken(accessToken: String)

    fun getIsFirstVisited(): Boolean

    fun setIsFirstVisited(isFirstVisited: Boolean)

    fun saveUserId(userId: Int)

    fun getUserId(): Int

    fun saveUserProfileNickname(profileNickname: String)

    fun getUserProfileNickname(): String

    fun saveUserProfileImageUrl(userProfileImageUrl: String)

    fun getUserProfileImageUrl(): String

    fun removeAccessToken()

    fun removeUserNickname()

    fun clearUserInfo()
}
