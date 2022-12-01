package com.yangbong.data.repository

import com.yangbong.domain.entity.request.DomainSearchRequest
import com.yangbong.domain.repository.SearchRepository
import javax.inject.Inject

class SearchRepositoryImpl @Inject constructor(

):SearchRepository{
    override fun addSearchData(data: String) {
        TODO("Not yet implemented")
    }

    override fun deleteData(data: String) {
        TODO("Not yet implemented")
    }

    override fun deleteAllData() {
        TODO("Not yet implemented")
    }

    override fun SearchOnClick() {
        TODO("Not yet implemented")
    }

    override fun RecentOnClick() {
        TODO("Not yet implemented")
    }

    override fun postSearch(searchRequest: DomainSearchRequest): Result<DomainSearchRequest> {
        TODO("Not yet implemented")
    }

}