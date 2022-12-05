package com.yangbong.domain.repository

import com.yangbong.domain.entity.ProfileInfo
import com.yangbong.domain.entity.response.DomainSearchResponse

interface SearchRepository {
    fun addSearchData(data: String)
    fun deleteData(data: String)
    fun deleteAllData()
    fun getRecentSearchData(): String
    suspend fun getSearch(searchRequest: String): Result<DomainSearchResponse>
    suspend fun getUserProfileInfo(userId: Int): Result<ProfileInfo>

}