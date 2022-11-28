package com.yangbong.set_profile.ui

import android.net.Uri
import androidx.activity.result.contract.ActivityResultContracts
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
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import kotlinx.coroutines.launch
import timber.log.Timber
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

    fun updateProfileIdState(state: SetProfileNicknameConstant) {
        _profileNicknameState.value = state
    }

    fun getProfileImage() {
        _profileImageUrl.postValue(setProfileRepository.getUserProfileImageUrl())
    }

    fun updateProfileImage(profileImageUri: Uri) {
        _profileImageUrl.postValue(profileImageUri.toString())
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
                setProfileRepository.saveUserId(it.userId)
                _navigateToSetCharacter.postValue(Event(true))
            }.onFailure {
                Timber.d(it)
            }
        }
    }

//    fun getImageFromGallery() {
//        viewModelScope.launch(exceptionHandler) {
//            this.galleryResult.launch("image/*")
//        }
//    }

//    fun getImageFromCamera() {
//        viewModelScope.launch(exceptionHandler) {
//            getCameraResult.launch(null)
//        }
//    }


}