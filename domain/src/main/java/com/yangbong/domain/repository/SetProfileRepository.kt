package com.yangbong.domain.repository

import com.yangbong.domain.entity.request.DomainSignUpRequest
import com.yangbong.domain.entity.response.DomainSignUpResponse

interface SetProfileRepository {

    suspend fun checkDuplicateProfileNickname(profileNickname: String): Result<Boolean>

    suspend fun postSignUp(signUpRequest: DomainSignUpRequest): Result<DomainSignUpResponse>

    fun saveUserId(userId: Int)

    fun saveUserProfileNickname(profileNickname: String)

    fun saveUserProfileImageUrl(profileImageUrl: String)

    fun getUserProfileImageUrl(): String
}