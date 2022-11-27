package com.yangbong.core_ui.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.yangbong.core_ui.util.Event
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import kotlinx.coroutines.CoroutineExceptionHandler
import java.security.cert.CertificateException

abstract class BaseViewModel : ViewModel() {
    private val compositeDisposable = CompositeDisposable()

    fun addDisposable(disposable: Disposable) {
        compositeDisposable.add(disposable)
    }

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }

    private val _moveToLogin = MutableLiveData<Event<Boolean>>()
    val moveToLogin: LiveData<Event<Boolean>> = _moveToLogin

    private val _errorMessage = MutableLiveData<Event<String>>()
    val errorMessage: LiveData<Event<String>> = _errorMessage

    val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        when (throwable) {
            is CertificateException -> _moveToLogin.postValue(Event(true))
            is RuntimeException -> _errorMessage.postValue(
                Event(
                    "권한을 허용하지 않으면 다메다메 서비스를 사용하기 어렵습니다.\n" +
                            "\n" +
                            "[설정] > [권한] 에서 권한을 허용해주세요."
                )
            )
        }
    }
}
