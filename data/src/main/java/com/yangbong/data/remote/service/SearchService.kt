package com.yangbong.data.remote.service

import com.yangbong.data.remote.call_adapter.NetworkState
import com.yangbong.data.remote.model.request.SearchRequest
import com.yangbong.data.remote.model.response.BaseResponse
import com.yangbong.data.remote.model.response.SearchResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface SearchService {
    @POST("v1/user/search")
    suspend fun postSearch(
        @Body body:SearchRequest
    ):NetworkState<BaseResponse<SearchResponse>>
}