package com.yangbong.domain.repository

import com.yangbong.domain.entity.request.DomainLoginRequest
import com.yangbong.domain.entity.response.DomainLoginResponse

interface LoginRepository {

    fun getFcmToken(tokenCallBack: (String) -> Unit)

    fun getAccessToken(): String

    fun saveAccessToken(accessToken: String)

    fun getIsFirstVisited(): Boolean

    fun setIsFirstVisited(isFirstVisit: Boolean)

    fun saveUserProfileId(userProfileId: String)

    fun saveUserProfileImageUrl(userProfileImageUrl: String)

    suspend fun postLogin(loginRequest: DomainLoginRequest): Result<DomainLoginResponse>

}