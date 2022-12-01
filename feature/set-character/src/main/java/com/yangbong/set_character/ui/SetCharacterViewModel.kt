package com.yangbong.set_character.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.yangbong.core_ui.base.BaseViewModel
import com.yangbong.core_ui.util.Event
import com.yangbong.domain.entity.CharacterInfo
import com.yangbong.domain.entity.request.DomainCharacterRequest
import com.yangbong.domain.repository.SetCharacterRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class SetCharacterViewModel @Inject constructor(
    private val setCharacterRepository: SetCharacterRepository
) : BaseViewModel() {

    private val characterList = mutableListOf<CharacterInfo>()

    /**
     * 하드코딩으로 구현 (임시)
     */
    init {
        for (characterId in MIN_CHARACTER_ID..MAX_CHARACTER_ID) {
            characterList.add(CharacterInfo(characterId))
        }
    }

    private val _navigateToHome = MutableLiveData<Event<Boolean>>()
    val navigateToHome: LiveData<Event<Boolean>> = _navigateToHome

    private val _characterInfoList = MutableLiveData<List<CharacterInfo>>()
    val characterInfoList: LiveData<List<CharacterInfo>> = _characterInfoList

    fun getCharacterList() {
        viewModelScope.launch {
            _characterInfoList.postValue(characterList)
        }
    }

    fun postCharacter(userId: Int, characterId: Int) {
        viewModelScope.launch {
            setCharacterRepository.postCharacter(
                DomainCharacterRequest(
                    userId = userId,
                    minion = characterId
                )
            ).onSuccess {
                _navigateToHome.postValue(Event(true))
            }.onFailure {
                Timber.d(it)
            }
        }
    }

    companion object {
        const val MIN_CHARACTER_ID = 1
        const val MAX_CHARACTER_ID = 9
    }
}
