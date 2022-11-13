package com.yangbong.domain.repository

import com.yangbong.domain.entity.request.DomainLoginRequest
import com.yangbong.domain.entity.response.DomainLoginResponse

interface LoginRepository {

    fun getAccessToken(): String

    fun saveAccessToken(accessToken: String)

    fun getIsFirstVisited(): Boolean

    fun setIsFirstVisited(isFirstVisit: Boolean)

    fun saveUserNickname(userNickname: String)

    fun saveUserProfileImageUrl(userProfileImageUrl: String)

    suspend fun postLogin(loginRequest: DomainLoginRequest): Result<DomainLoginResponse>

}