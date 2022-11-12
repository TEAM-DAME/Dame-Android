package com.yangbong.domain.repository

interface LoginRepository {

    fun getAccessToken(): String

    fun saveAccessToken(accessToken: String)

    fun getIsFirstVisited(): Boolean

    fun setIsFirstVisited(isFirstVisit: Boolean)

    fun saveUserNickname(userNickname: String)

    fun saveUserProfileImageUrl(userProfileImageUrl: String)

//    suspend fun postLogin(loginRequest: DomainLoginRequest): Result<DomainLoginResponse>

}