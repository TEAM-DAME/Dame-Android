package com.yangbong.data.local.datasource

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

    override fun removeAccessToken() {
        localPreferences.edit { remove(DAME_DAME_ACCESS_TOKEN) }
    }

    companion object {
        const val DAME_DAME_ACCESS_TOKEN = "DAME_DAME_ACCESS_TOKEN"
    }
}
