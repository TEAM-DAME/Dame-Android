package com.yangbong.main.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.yangbong.core_ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

class SearchViewModel @Inject constructor(

) : BaseViewModel() {
    private val _searchdata = MutableLiveData<ArrayList<String>>()
    val searchdata: LiveData<ArrayList<String>> = _searchdata
    private var items=ArrayList<String>()
    fun addSearchData(data:String){
        if(items.contains(data)){
        }
        else{
            items.add(data)
            _searchdata.value=items
        }
    }
    fun deleteData(data:String){
            items.remove(data)
            _searchdata.value=items
    }
    fun deleteAllData(){
        items.clear()
        _searchdata.value=items
    }

    fun searchOnClick(){

    }
    fun recentOnClick(){

    }
}