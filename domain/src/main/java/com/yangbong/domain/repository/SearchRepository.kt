package com.yangbong.domain.repository

import com.yangbong.domain.entity.request.DomainSearchRequest

interface SearchRepository {
    fun addSearchData(data:String)
    fun deleteData(data:String)
    fun deleteAllData()
    fun SearchOnClick()
    fun RecentOnClick()
    fun postSearch(searchRequest: DomainSearchRequest):Result<DomainSearchRequest>

}