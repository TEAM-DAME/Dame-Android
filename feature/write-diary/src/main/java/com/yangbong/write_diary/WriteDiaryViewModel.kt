package com.yangbong.write_diary

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.yangbong.core_ui.base.BaseViewModel
import com.yangbong.domain.entity.request.DomainSentimentAnalyzeRequest
import com.yangbong.domain.entity.request.DomainWriteDiaryRequest
import com.yangbong.domain.repository.SentimentAnalyzeRepository
import com.yangbong.domain.repository.WriteDiaryRepository
import kotlinx.coroutines.launch
import timber.log.Timber
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject


class WriteDiaryViewModel @Inject constructor(
    private val writeDiaryRepository: WriteDiaryRepository,
    private val sentimentAnalyzeRepository: SentimentAnalyzeRepository
):BaseViewModel(){
    var _userId=MutableLiveData<Int>()
    var _sentiment=MutableLiveData<String>()
    var _positive=MutableLiveData<Double>()
    var _neutral=MutableLiveData<Double>()
    var _negative=MutableLiveData<Double>()
    var _content=MutableLiveData<String>()
    var _title=MutableLiveData<String>()

    fun getUserId(){
        _userId.postValue(writeDiaryRepository.getUserId())
    }
    fun getData(content:String,title:String){
        _content.postValue(content)
        _title.postValue(title)
    }
    fun postSentiment(content:String){
        viewModelScope.launch {
            sentimentAnalyzeRepository.postEmotion(
                DomainSentimentAnalyzeRequest(
                    content = content
                )
            ).onSuccess {
                _positive.postValue(it.positive)
                _negative.postValue(it.negative)
                _neutral.postValue(it.negative)
                _sentiment.postValue(it.sentiment)
                Timber.d(it.toString())
                postWriteDiary(_userId.value!!,_title.value!!,_content.value!!,_positive.value!!,_neutral.value!!,_neutral.value!!)
            }.onFailure { Timber.d(it) }

        }


    }

    fun postWriteDiary(userId:Int,title:String,content:String,positive:Double,neutral:Double,negative:Double){
        viewModelScope.launch {
            writeDiaryRepository.postWriteDiary(
                DomainWriteDiaryRequest(
                    userId=userId,
                    title=title,
                    content=content,
                    positive=positive,
                    neutral=neutral,
                    negative=negative
                )
            ).onSuccess { 
                // 화면 전환 ? method 구현
                Timber.d(it) }
                .onFailure { Timber.d(it) }
        }
    }

}