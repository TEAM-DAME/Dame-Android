package com.yangbong.domain.repository

import com.yangbong.domain.entity.request.DomainSetProfileRequest
import com.yangbong.domain.entity.response.DomainSetProfileResponse

interface SetProfileRepository {

    suspend fun checkDuplicateProfileNickname(profileNickname: String): Result<Boolean>

    suspend fun postUserProfile(setProfileRequest: DomainSetProfileRequest): Result<DomainSetProfileResponse>

    fun saveUserProfileId(profileId: String)

    fun saveUserProfileImageUrl(profileImageUrl: String)

    fun getUserProfileImageUrl(): String
}