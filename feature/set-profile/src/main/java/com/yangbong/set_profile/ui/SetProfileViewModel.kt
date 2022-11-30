package com.yangbong.set_profile.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.yangbong.core_ui.base.BaseViewModel
import com.yangbong.core_ui.constant.SetProfileNicknameConstant
import com.yangbong.core_ui.constant.SetProfileNicknameConstant.*
import com.yangbong.core_ui.util.Event
import com.yangbong.domain.entity.request.DomainSignUpRequest
import com.yangbong.domain.repository.SetProfileRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import java.io.File
import javax.inject.Inject

@HiltViewModel
class SetProfileViewModel @Inject constructor(
    private val setProfileRepository: SetProfileRepository
) : BaseViewModel() {

    var profileNickname = MutableLiveData("")

    private val _profileNicknameState = MutableLiveData<SetProfileNicknameConstant>()
    val profileIdState: LiveData<SetProfileNicknameConstant> = _profileNicknameState

    private val _profileImageUrl = MutableLiveData<String>()
    val profileImageUrl: LiveData<String> = _profileImageUrl

    private val _navigateToSetCharacter = MutableLiveData<Event<Boolean>>()
    val navigateToSetCharacter: LiveData<Event<Boolean>> = _navigateToSetCharacter

    private val _userId = MutableLiveData<Int>()
    val userId: LiveData<Int> = _userId

    fun updateProfileIdState(state: SetProfileNicknameConstant) {
        _profileNicknameState.value = state
    }

    fun getProfileImage() {
        _profileImageUrl.postValue(setProfileRepository.getUserProfileImageUrl())
    }

    fun checkDuplicateNickName() {
        viewModelScope.launch {
            setProfileRepository.checkDuplicateProfileNickname(
                profileNickname.value ?: throw IllegalStateException()
            )
                .onSuccess {
                    if (it) {
                        _profileNicknameState.postValue(ALLOWED_NICKNAME)
                    } else {
                        _profileNicknameState.postValue(DUPLICATE_NICKNAME)
                    }
                }
                .onFailure {
                    Timber.d(it)
                }
        }
    }

    fun postSignUp(platform: String, socialToken: String, fcmToken: String) {
        viewModelScope.launch {
            setProfileRepository.postSignUp(
                DomainSignUpRequest(
                    platform = platform,
                    socialToken = socialToken,
                    fcmToken = fcmToken,
                    nickname = profileNickname.value ?: "",
                    profileImageUrl = profileImageUrl.value ?: ""
                )
            ).onSuccess {
                _userId.postValue(it.userId)
                setProfileRepository.saveUserId(it.userId)
                _navigateToSetCharacter.postValue(Event(true))
            }.onFailure {
                Timber.d(it)
            }
        }
    }

    fun uploadAndDownloadFile(file: File) {
        viewModelScope.launch(exceptionHandler) {
            setProfileRepository.uploadAndDownloadFile(file) {
                if (it.isNotEmpty()) _profileImageUrl.postValue(it)
            }
        }
    }
}