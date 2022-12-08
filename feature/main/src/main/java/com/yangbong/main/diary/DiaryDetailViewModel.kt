package com.yangbong.main.diary

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.yangbong.core_ui.base.BaseViewModel
import com.yangbong.domain.entity.Diary
import com.yangbong.domain.repository.DiaryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class DiaryDetailViewModel @Inject constructor(
    private val diaryRepository: DiaryRepository
) : BaseViewModel() {

    private val _diaryContent = MutableLiveData<Diary>()
    val diaryContent: LiveData<Diary> = _diaryContent

    fun getDiaryContent(userId: Int, diaryId: Int) {
        viewModelScope.launch {
            diaryRepository.getDiaryContent(
                userId, diaryId
            ).onSuccess {
                _diaryContent.postValue(it)
            }.onFailure {
                Timber.d(it)
            }
        }
    }

}