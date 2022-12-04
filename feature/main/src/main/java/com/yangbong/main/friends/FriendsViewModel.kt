package com.yangbong.main.friends

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yangbong.core_ui.base.BaseViewModel
import com.yangbong.domain.entity.ProfileInfo
import com.yangbong.domain.repository.FriendsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class FriendsViewModel @Inject constructor(
    private val friendsRepository: FriendsRepository
):BaseViewModel(){
    private val _userProfileData=MutableLiveData<ProfileInfo>()
    val userProfileData: LiveData<ProfileInfo> =_userProfileData

    fun getUserProfileInfo(userId:Int){
        viewModelScope.launch {
            friendsRepository.getUserProfileInfo(userId)
                .onSuccess {
                    _userProfileData.postValue(it)
                }
                .onFailure {
                    Timber.d(it.message.toString())
                }
        }
    }
}