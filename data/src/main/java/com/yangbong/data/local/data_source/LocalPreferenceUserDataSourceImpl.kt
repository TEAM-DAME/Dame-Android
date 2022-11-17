package com.yangbong.data.local.data_source

import android.content.SharedPreferences
import androidx.core.content.edit
import javax.inject.Inject

class LocalPreferenceUserDataSourceImpl @Inject constructor(
    private val localPreferences: SharedPreferences
) : LocalPreferenceUserDataSource {
    override fun getAccessToken(): String =
        localPreferences.getString(DAME_DAME_ACCESS_TOKEN, "") ?: ""

    override fun saveAccessToken(accessToken: String) {
        localPreferences.edit { putString(DAME_DAME_ACCESS_TOKEN, accessToken) }
    }

    override fun getIsFirstVisited(): Boolean =
        localPreferences.getBoolean(IS_FIRST_VISITED, true)

    override fun setIsFirstVisited(isFirstVisited: Boolean) {
        localPreferences.edit { putBoolean(IS_FIRST_VISITED, isFirstVisited) }
    }

    override fun getUserProfileId(): String =
        localPreferences.getString(USER_NICKNAME, "") ?: ""

    override fun saveUserProfileId(profileId: String) {
        localPreferences.edit { putString(USER_NICKNAME, profileId) }
    }

    override fun getUserProfileImageUrl(): String =
        localPreferences.getString(USER_PROFILE_IMAGE_URL, "") ?: ""

    override fun saveUserProfileImageUrl(userProfileImageUrl: String) {
        localPreferences.edit { putString(USER_PROFILE_IMAGE_URL, userProfileImageUrl) }
    }

    override fun removeAccessToken() {
        localPreferences.edit { remove(DAME_DAME_ACCESS_TOKEN) }
    }

    override fun removeUserNickname() {
        localPreferences.edit { remove(USER_NICKNAME) }
    }

    override fun clearUserInfo() {
        localPreferences.edit { clear() }
    }

    override fun saveUserId(userid: Int) {
        localPreferences.edit{ putInt(USER_ID,userid)}
    }

    override fun saveInitCharacter(minion: Int) {
        localPreferences.edit{ putInt(INIT_CHARACTER,minion)}
    }

    companion object {
        const val DAME_DAME_ACCESS_TOKEN = "DAME_DAME_ACCESS_TOKEN"
        const val USER_NICKNAME = "USER_NICKNAME"
        const val USER_PROFILE_IMAGE_URL = "USER_PROFILE_IMAGE_URL"
        const val IS_FIRST_VISITED = "IS_FIRST_VISITED"
        const val USER_ID = "USER_ID"
        const val INIT_CHARACTER = "INIT_CHARACTER"
    }
}
