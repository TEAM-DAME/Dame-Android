package com.yangbong.data.remote.service

import com.yangbong.data.remote.call_adapter.NetworkState
import com.yangbong.data.remote.model.request.SetCharacterRequest
import com.yangbong.data.remote.model.response.EmptyResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface SetCharacterService {

    @POST("v1/user/minion")
    suspend fun postCharacter(
        @Body body: SetCharacterRequest
    ): NetworkState<EmptyResponse>
}