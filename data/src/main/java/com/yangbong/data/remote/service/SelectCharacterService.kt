package com.yangbong.data.remote.service

import com.yangbong.data.remote.call_adapter.NetworkState
import com.yangbong.data.remote.model.request.SelectCharacterRequest
import com.yangbong.data.remote.model.response.BaseResponse
import com.yangbong.data.remote.model.response.SelectCharacterResponse
import retrofit2.http.Body
import retrofit2.http.PUT

interface SelectCharacterService {
    @PUT("user/character")
    suspend fun putInitCharacter(
        @Body body: SelectCharacterRequest
    ): NetworkState<BaseResponse<SelectCharacterResponse>>
}