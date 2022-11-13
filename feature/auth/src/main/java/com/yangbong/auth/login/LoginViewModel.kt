package com.yangbong.auth.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yangbong.core_ui.util.Event
import com.yangbong.domain.entity.request.DomainLoginRequest
import com.yangbong.domain.use_case.login.LoginUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCases: LoginUseCases
) : ViewModel() {

    private val _socialToken = MutableLiveData<String>()
    val socialToken: LiveData<String> = _socialToken

    private val _fcmToken = MutableLiveData<String>()
    val fcmToken: LiveData<String> = _fcmToken

    private lateinit var platform: String

    private val _navigateToHome = MutableLiveData<Event<Boolean>>()
    val navigateToHome: LiveData<Event<Boolean>> = _navigateToHome

    private val _loginFailureMessage = MutableLiveData<String>()
    val loginFailureMessage: LiveData<String> = _loginFailureMessage

    fun postLogin() {
        viewModelScope.launch {
            loginUseCases.postLogin(
                DomainLoginRequest(
                    socialToken = socialToken.value ?: "",
                    fcmToken = fcmToken.value ?: ""
                )
            ).onSuccess {
                loginUseCases.saveAccessToken(it.accessToken ?: "")
            }.onFailure {
                _loginFailureMessage.value = it.message
            }
        }
    }


    fun getAccessToken(): String =
        loginUseCases.getAccessToken()

    fun updateSocialToken(socialToken: String) {
        _socialToken.value = socialToken
    }

    fun updatePlatform(platform: String) {
        this.platform = platform
    }

    fun getFcmToken() {
        loginUseCases.getFcmToken {
            updateFcmToken(it)
        }
    }

    private fun updateFcmToken(fcmToken: String) {
        _fcmToken.value = fcmToken
    }
}