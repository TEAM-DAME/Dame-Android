package com.yangbong.main.pocket

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yangbong.core_ui.base.BaseViewModel
import com.yangbong.domain.entity.CharacterInfo
import com.yangbong.domain.repository.PocketRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class PocketViewModel @Inject constructor(
    private val pocketRepository: PocketRepository
) : BaseViewModel() {
    private val _characterList = MutableLiveData<ArrayList<Int>>()
    val characterList: LiveData<ArrayList<Int>> = _characterList
    private val _userId = MutableLiveData<Int>()
    val userId: LiveData<Int> = _userId
    private val _userNickName = MutableLiveData<String>()
    val userNickname: LiveData<String> = _userNickName

    fun getUserPocket(userId: Int) {
        viewModelScope.launch {
            pocketRepository.getUserPocket(userId)
                .onSuccess {
                    _characterList.postValue(it.data)
                }
                .onFailure {
                    Timber.d(it.message.toString())
                }
        }
    }

    fun getUserId() {
        _userId.postValue(pocketRepository.getUserId())
    }

    fun getUserNickName() {
        _userNickName.postValue(pocketRepository.getUserNickName())
    }
}