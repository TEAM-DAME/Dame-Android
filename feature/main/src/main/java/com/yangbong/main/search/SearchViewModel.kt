package com.yangbong.main.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.yangbong.core_ui.base.BaseViewModel
import com.yangbong.domain.entity.SearchInfo
import com.yangbong.domain.entity.request.DomainSearchRequest
import com.yangbong.domain.repository.SearchRepository
import kotlinx.coroutines.launch
import org.json.JSONArray
import javax.inject.Inject

class SearchViewModel @Inject constructor(
    private val searchRepository: SearchRepository
) : BaseViewModel() {
    private val _searchData = MutableLiveData<ArrayList<String>>()
    val searchData: LiveData<ArrayList<String>> = _searchData
    private var items=ArrayList<String>()
    //
    private val _searchResultData=MutableLiveData<List<SearchInfo>>()
    val searchResultData:LiveData<List<SearchInfo>> =_searchResultData
    private val _profileImgUrl=MutableLiveData<String>()
    val profileImgUrl:LiveData<String> =_profileImgUrl
    fun addSearchDataView(data:String){
        if(items.contains(data)){
        }
        else{
            items.add(data)
            _searchData.value=items
        }
        searchRepository.addSearchData(data)
    }
    fun deleteDataView(data:String){
        items.remove(data)
        _searchData.value=items
        searchRepository.deleteData(data)
    }
    fun deleteAllDataView(){
        items.clear()
        _searchData.value=items
        searchRepository.deleteAllData()
    }
    fun getRecentSearchData(){
        val sharedData=searchRepository.getRecentSearchData()
        var recentSearchJsonArr= JSONArray(sharedData)
        var recentSearchArr=ArrayList<String>()
        for(i in 0 until recentSearchJsonArr.length()){
            recentSearchArr.add(recentSearchJsonArr.optString(i))
        }
        _searchData.value=recentSearchArr

    }

    fun getSearch(searchContent:String){
        viewModelScope.launch {
            searchRepository.getSearch(
                DomainSearchRequest(
                    searchContent = searchContent
                )
            ).onSuccess {
                _searchResultData.postValue(it.searchDataList)
            }
        }

    }
    //----------------Search Result -------------- //
    fun getProfileImg(imgUrl:String){
        _profileImgUrl.postValue(imgUrl)
    }


}