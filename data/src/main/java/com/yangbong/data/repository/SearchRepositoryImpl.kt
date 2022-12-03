package com.yangbong.data.repository

import com.google.gson.JsonArray
import com.yangbong.core_data.exception.RetrofitFailureStateException
import com.yangbong.data.local.data_source.LocalPreferenceUserDataSource
import com.yangbong.data.remote.call_adapter.NetworkState
import com.yangbong.data.remote.data_source.RemoteSearchSource
import com.yangbong.data.remote.mapper.SearchMapper
import com.yangbong.data.remote.model.request.SearchRequest
import com.yangbong.domain.entity.request.DomainSearchRequest
import com.yangbong.domain.entity.response.DomainSearchResponse
import com.yangbong.domain.repository.SearchRepository
import org.json.JSONArray
import javax.inject.Inject

class SearchRepositoryImpl @Inject constructor(
    private val remoteSearchSource:RemoteSearchSource,
    private val searchMapper:SearchMapper,
    private val localPreferenceUserDataSource: LocalPreferenceUserDataSource

):SearchRepository {
    override fun addSearchData(data: String) {
        val sharedData=localPreferenceUserDataSource.getRecentSearchData()
        var dataJsonArr= JSONArray(sharedData)
        dataJsonArr.put(data)
        localPreferenceUserDataSource.setRecentSearchData(dataJsonArr.toString())

    }

    override fun deleteData(data: String) {
        val sharedData=localPreferenceUserDataSource.getRecentSearchData()
        var dataArr=ArrayList<String>()
        var dataJsonArr= JSONArray(sharedData)
        for(i in 0 until dataJsonArr.length()){
            dataArr.add(dataJsonArr.optString(i))
        }
        dataArr.remove(data)
        var deleteJsonArray=JSONArray()
        for(i in dataArr){
            deleteJsonArray.put(i)
        }
        localPreferenceUserDataSource.setRecentSearchData(deleteJsonArray.toString())


    }

    override fun deleteAllData() {
        localPreferenceUserDataSource.removeRecentSearchData()
    }

    override fun getRecentSearchData(): String {
        return localPreferenceUserDataSource.getRecentSearchData()
    }


    override suspend fun getSearch(searchRequest: DomainSearchRequest): Result<DomainSearchResponse> {
        val searchResult = remoteSearchSource.getSearch(
            SearchRequest(
                searchContent = searchRequest.searchContent
            )
        )
        when (searchResult) {
            is NetworkState.Success -> return Result.success(
                DomainSearchResponse(
                    searchDataList = searchResult.body.data.searchData.map {
                        searchMapper.toSearchInfo(it)
                    }
                ))
            is NetworkState.Failure ->
                return Result.failure(
                    RetrofitFailureStateException(
                        searchResult.error,
                        searchResult.code
                    )
                )
            else ->
                return Result.failure(
                    IllegalStateException(
                        "UnKnownError please check "
                    )
                )


        }
    }
}

