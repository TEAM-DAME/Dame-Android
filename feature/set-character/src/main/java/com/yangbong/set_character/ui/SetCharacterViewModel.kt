package com.yangbong.set_character

import com.yangbong.core_ui.util.Event
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.yangbong.core_ui.base.BaseViewModel
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

    private val _userId = MutableLiveData<Int>()
    val userId: LiveData<Int> = _userId

    private val _minionId = MutableLiveData<Int>()
    val minionId: LiveData<Int> = _minionId

    private val _navigateToMainActivity = MutableLiveData<Event<Boolean>>()
    val navigateToMainActivity: LiveData<Event<Boolean>> = _navigateToMainActivity

    fun getUserId() {
        _userId.postValue(setCharacterRepository.getUserId())
    }

    fun postCharacter(minion: Int, userId: Int) {
        viewModelScope.launch {
            setCharacterRepository.postCharacter(
                DomainCharacterRequest(
                    userId = userId,
                    minion = minion
                )
            ).onSuccess {
                _navigateToMainActivity.postValue(Event(true))
            }.onFailure {
                Timber.d(it)
                _navigateToMainActivity.postValue(Event(true))
            }
        }
    }

}
