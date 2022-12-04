package com.yangbong.main.friends

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yangbong.core_ui.base.BaseViewModel
import com.yangbong.domain.entity.ProfileInfo
import com.yangbong.domain.entity.SearchInfo
import com.yangbong.domain.repository.FriendsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class FriendsViewModel @Inject constructor(
    private val friendsRepository: FriendsRepository
):BaseViewModel(){
    private val _friendListData=MutableLiveData<ArrayList<SearchInfo>>()
    val friendListData:LiveData<ArrayList<SearchInfo>> =_friendListData
    private val _userProfileData=MutableLiveData<ProfileInfo>()
    val userProfileData: LiveData<ProfileInfo> =_userProfileData
    private val _userId=MutableLiveData<Int>()
    val userId:LiveData<Int> =_userId
    private val _userNickName=MutableLiveData<String>()
    val userNickname:LiveData<String> =_userNickName

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
    fun getUserId(){
        _userId.postValue(friendsRepository.getUserId())
    }
    fun getUserNickName(){
        _userNickName.postValue(friendsRepository.getUserNickName())
    }

    fun getFriendList(userId:Int,page:Int,size:Int){
        viewModelScope.launch {
            friendsRepository.getFriendList(userId,page,size)
                .onSuccess {
                    _friendListData.postValue(it.results)
                }
                .onFailure {
                    Timber.d(it.message.toString())
                }
        }
    }
}