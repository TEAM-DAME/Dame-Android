package com.yangbong.set_profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yangbong.core_ui.util.Event
import com.yangbong.domain.entity.request.DomainSelectCharacterRequest
import com.yangbong.domain.repository.SelectCharacterRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class SelectCharacterViewModel @Inject constructor(
    private val selectCharacterRepository: SelectCharacterRepository
):ViewModel() {
    var userid = MutableLiveData<Int>()

    var minion = MutableLiveData<Int>()

    private val _navigateToExplainCharacter = MutableLiveData<Event<Boolean>>()
    val navigateToSetCharacter: LiveData<Event<Boolean>> = _navigateToExplainCharacter

    fun putInitCharacter(){
        viewModelScope.launch {
            selectCharacterRepository.putInitCharacter(
                DomainSelectCharacterRequest(
                    userid = userid.value?:-1,
                    minion = minion.value?:-1
                )
            ).onSuccess {
                if(it.message =="200ok"){
                    selectCharacterRepository.saveUserId(
                        userid.value?:-1
                    )
                    selectCharacterRepository.saveInitCharacter(
                        minion.value?:-1
                    )
                    _navigateToExplainCharacter.postValue(Event(true))
                }
            }.onFailure {
                Timber.d(it)
            }
        }
    }
}