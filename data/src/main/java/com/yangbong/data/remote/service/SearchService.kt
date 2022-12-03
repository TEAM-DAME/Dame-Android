package com.yangbong.data.remote.service

import com.yangbong.data.remote.call_adapter.NetworkState
import com.yangbong.data.remote.model.request.SearchRequest
import com.yangbong.data.remote.model.response.BaseResponse
import com.yangbong.data.remote.model.response.SearchResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchService {
    @GET("v1/user/search?query=")
    suspend fun getSearch(
        @Query("searchContent") query:SearchRequest
    ):NetworkState<BaseResponse<SearchResponse>>
}