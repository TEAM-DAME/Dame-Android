package com.yangbong.data.remote.service

import com.yangbong.data.remote.call_adapter.NetworkState
import com.yangbong.data.remote.model.response.BaseResponse
import com.yangbong.data.remote.model.response.CheckDuplicateProfileIdResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface CheckDuplicateProfileIdService {

    @GET("user/nickname")
    suspend fun checkDuplicateProfileId(
        @Query("query") nickName: String
    ): NetworkState<BaseResponse<CheckDuplicateProfileIdResponse>>
}
