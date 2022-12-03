package com.yangbong.domain.repository

import com.yangbong.domain.entity.request.DomainSearchRequest
import com.yangbong.domain.entity.response.DomainSearchResponse

interface SearchRepository {
    fun addSearchData(data:String)
    fun deleteData(data:String)
    fun deleteAllData()
    fun getRecentSearchData():String
    suspend fun getSearch(searchRequest: DomainSearchRequest): Result<DomainSearchResponse>
}