package com.yangbong.set_profile

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yangbong.core_ui.constant.SetProfileIdConstant
import com.yangbong.core_ui.constant.SetProfileIdConstant.*
import com.yangbong.core_ui.util.Event
import com.yangbong.domain.entity.request.DomainSetProfileRequest
import com.yangbong.domain.repository.SetProfileRepository
import com.yangbong.domain.use_case.login.SaveUserProfileImageUrl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class SetProfileViewModel @Inject constructor(
    private val setProfileRepository: SetProfileRepository
) : ViewModel() {

    var profileId = MutableLiveData("")

    private val _profileIdState = MutableLiveData<SetProfileIdConstant>()
    val profileIdState: LiveData<SetProfileIdConstant> = _profileIdState

    private val _profileImageUrl = MutableLiveData<String>()
    val profileImageUrl: LiveData<String> = _profileImageUrl

    private val _navigateToSetCharacter = MutableLiveData<Event<Boolean>>()
    val navigateToSetCharacter: LiveData<Event<Boolean>> = _navigateToSetCharacter

    fun updateProfileIdState(state: SetProfileIdConstant) {
        _profileIdState.value = state
    }

    fun getProfileImage() {
        _profileImageUrl.postValue(setProfileRepository.getUserProfileImageUrl())
    }

    fun checkDuplicateNickName() {
        viewModelScope.launch {
            setProfileRepository.checkDuplicateProfileId(profileId.value ?: throw IllegalStateException())
                .onSuccess {
                    if (it) {
                        _profileIdState.postValue(ALLOWED_NICKNAME)
                    } else {
                        _profileIdState.postValue(DUPLICATE_NICKNAME)
                    }
                }
                .onFailure {
                    Timber.d(it)
                }
        }
    }

    fun postSetProfile() {
        viewModelScope.launch {
            setProfileRepository.postUserProfile(
                DomainSetProfileRequest(
                    profileId = profileId.value ?: "",
                    profileImgUrl = profileImageUrl.value ?: ""
                )
            ).onSuccess {
                setProfileRepository.saveUserProfileId(
                    it.userInfo.profileId ?: throw IllegalStateException()
                )
                setProfileRepository.saveUserProfileImageUrl(
                    it.userInfo.profileImageUrl ?: throw IllegalStateException()
                )
                _navigateToSetCharacter.postValue(Event(true))
            }.onFailure {
                Timber.d(it)
            }
        }
    }
}