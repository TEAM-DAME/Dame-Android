package com.yangbong.data.remote.mapper

import com.yangbong.data.remote.model.response.Character
import com.yangbong.domain.entity.CharacterInfo
import javax.inject.Inject

class CharacterMapper @Inject constructor() {

    fun toCharacterInfo(characterItem: Character): CharacterInfo =
        CharacterInfo(
            minionId = characterItem.minionId
        )
}