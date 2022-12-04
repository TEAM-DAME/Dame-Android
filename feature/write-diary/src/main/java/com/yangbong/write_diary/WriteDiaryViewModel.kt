package com.yangbong.write_diary

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.yangbong.core_ui.base.BaseViewModel
import com.yangbong.core_ui.util.Event
import com.yangbong.core_ui.util.UiState
import com.yangbong.domain.entity.EmotionInfo
import com.yangbong.domain.entity.request.DomainDiaryRequest
import com.yangbong.domain.repository.WriteDiaryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class WriteDiaryViewModel @Inject constructor(
    private val writeDiaryRepository: WriteDiaryRepository
) : BaseViewModel() {

    var title = MutableLiveData("")
    var content = MutableLiveData("")
    var date = MutableLiveData("")

    private val _emotionValue = MutableLiveData<Double>()
    val emotionValue: LiveData<Double> = _emotionValue

    private val _writeDiaryUiState: MutableStateFlow<UiState<EmotionInfo>> =
        MutableStateFlow(UiState.Loading)
    val writeDiaryUiState: StateFlow<UiState<EmotionInfo>>
        get() = _writeDiaryUiState.asStateFlow()

    private val _postDiary = MutableLiveData<Event<Boolean>>()
    val postDiary: LiveData<Event<Boolean>> = _postDiary

    private val _moveToHome = MutableLiveData<Event<Boolean>>()
    val moveToHome: LiveData<Event<Boolean>> = _moveToHome

    private val _userId = MutableLiveData<Int>()
    val userId: LiveData<Int> = _userId

    fun postSentiment(content: String) {
        viewModelScope.launch {
            writeDiaryRepository.postSentiment(content)
                .onSuccess {
                    _writeDiaryUiState.value = UiState.Success(it)
                    _postDiary.postValue(Event(true))
                    _emotionValue.postValue(it.emotionValue.toDouble())
                    Timber.tag("okhttp").d("감정분석 성공!!! ${it.emotionType}")
                }
                .onFailure {
                    _writeDiaryUiState.value = UiState.Failure(it.message)
                    Timber.tag("okhttp").d("감정분석 실패!!!")
                }
        }
    }

    fun postDiary(userId: Int) {
        viewModelScope.launch {
            writeDiaryRepository.postDiary(
                DomainDiaryRequest(
                    userId = userId,
                    title = title.value ?: "",
                    content = content.value ?: "",
                    emotionValue = emotionValue.value ?: 0.0
                )
            ).onSuccess {
                _moveToHome.postValue(Event(true))
            }.onFailure {
                // TODO ("임시")
                _moveToHome.postValue(Event(true))
            }
        }
    }

    fun getUserId() {
        val userId = writeDiaryRepository.getUserId()
        _userId.postValue(userId)
    }

}