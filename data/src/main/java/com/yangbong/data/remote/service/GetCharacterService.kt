package com.yangbong.data.remote.service

import com.yangbong.data.remote.call_adapter.NetworkState
import com.yangbong.data.remote.model.response.BaseResponse
import com.yangbong.data.remote.model.response.CharacterResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface GetCharacterService {

    @GET("v1/user/home/{userId}")
    suspend fun getCharacter(
        @Path("userId") userId: Int
    ): NetworkState<BaseResponse<CharacterResponse>>
}