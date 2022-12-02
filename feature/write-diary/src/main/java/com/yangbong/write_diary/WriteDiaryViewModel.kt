package com.yangbong.write_diary

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.yangbong.core_ui.base.BaseViewModel
import com.yangbong.domain.entity.EmotionInfo
import com.yangbong.domain.repository.WriteDiaryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class WriteDiaryViewModel @Inject constructor(
    private val writeDiaryRepository: WriteDiaryRepository
) : BaseViewModel() {

    var title = MutableLiveData("")
    var content = MutableLiveData("")

    private val _emotionInfo = MutableLiveData<EmotionInfo>()
    val emotionInfo: LiveData<EmotionInfo> = _emotionInfo

    
}