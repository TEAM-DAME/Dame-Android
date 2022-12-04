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
import java.lang.Double.max
import javax.inject.Inject

@HiltViewModel
class WriteDiaryViewModel @Inject constructor(
    private val writeDiaryRepository: WriteDiaryRepository
) : BaseViewModel() {

    var title = MutableLiveData("")
    var content = MutableLiveData("")
    var date = MutableLiveData("")

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

    private var positiveValue : Double = 0.0
    private var neutralValue : Double = 0.0
    private var negativeValue : Double = 0.0

    fun postSentiment(content: String) {
        viewModelScope.launch {
            writeDiaryRepository.postSentiment(content)
                .onSuccess {
                    _writeDiaryUiState.value = UiState.Success(it)
                    _postDiary.postValue(Event(true))
                    positiveValue = it.positive
                    neutralValue = it.neutral
                    negativeValue = it.negative
                    Timber.tag("okhttp").d("감정분석 성공!!! ${max(max(it.positive, it.neutral), it.negative)}")
                }
                .onFailure {
                    _writeDiaryUiState.value = UiState.Failure(it.message)
                    Timber.tag("okhttp").d("감정분석 실패!!!")
                }
        }
    }

    fun postDiary(title: String, content: String) {
        viewModelScope.launch {
            writeDiaryRepository.postDiary(
                DomainDiaryRequest(
                    minionId = 1,
                    userId = userId.value ?: -1,
                    title = title,
                    content = content,
                    positive = positiveValue,
                    neutral = neutralValue,
                    negative = negativeValue
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