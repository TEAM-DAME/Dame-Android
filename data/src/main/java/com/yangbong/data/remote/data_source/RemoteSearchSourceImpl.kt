package com.yangbong.data.remote.data_source

import com.yangbong.data.remote.call_adapter.NetworkState
import com.yangbong.data.remote.model.request.SearchRequest
import com.yangbong.data.remote.model.response.BaseResponse
import com.yangbong.data.remote.model.response.SearchResponse
import com.yangbong.data.remote.service.SearchService
import javax.inject.Inject

class RemoteSearchSourceImpl @Inject constructor(
    private val searchService:SearchService
):RemoteSearchSource{
    override suspend fun getSearch(searchRequest: SearchRequest): NetworkState<BaseResponse<SearchResponse>> {
        return searchService.getSearch(searchRequest)
    }

}