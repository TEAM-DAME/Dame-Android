package com.yangbong.data.remote.data_source

import com.yangbong.data.remote.call_adapter.NetworkState
import com.yangbong.data.remote.model.response.BaseResponse
import com.yangbong.data.remote.model.response.CharacterResponse
import com.yangbong.data.remote.service.GetCharacterService
import javax.inject.Inject

class RemoteGetCharacterDataSourceImpl @Inject constructor(
    private val characterService: GetCharacterService
) : RemoteGetCharacterDataSource {
    override suspend fun getCharacterInfo(userId: Int): NetworkState<BaseResponse<CharacterResponse>> {
        return characterService.getCharacter(userId)
    }
}