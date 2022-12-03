package com.yangbong.data.remote.data_source

import com.yangbong.data.remote.call_adapter.NetworkState
import com.yangbong.data.remote.model.request.SearchRequest
import com.yangbong.data.remote.model.response.BaseResponse
import com.yangbong.data.remote.model.response.SearchResponse

interface RemoteSearchSource {
    suspend fun getSearch(searchRequest: SearchRequest):NetworkState<BaseResponse<SearchResponse>>
}