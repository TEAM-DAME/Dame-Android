package com.yangbong.data.local.data_source

interface LocalPreferenceUserDataSource {
    fun getAccessToken(): String

    fun saveAccessToken(accessToken: String)

    fun getIsFirstVisited(): Boolean

    fun setIsFirstVisited(isFirstVisited: Boolean)

    fun saveUserProfileId(profileId: String)

    fun getUserProfileId(): String

    fun saveUserProfileImageUrl(userProfileImageUrl: String)

    fun getUserProfileImageUrl(): String

    fun removeAccessToken()

    fun removeUserNickname()

    fun clearUserInfo()

    fun saveUserId(userid: Int)

    fun saveInitCharacter(minion: Int)
}
