package com.yangbong.data.remote.mapper

import com.yangbong.data.local.data_source.LocalPreferenceUserDataSource
import com.yangbong.data.remote.model.response.SearchData
import com.yangbong.domain.entity.SearchInfo
import javax.inject.Inject

class SearchMapper @Inject constructor(
    val localPreferenceUserDataSource: LocalPreferenceUserDataSource
) {
    fun toSearchInfo(searchData:SearchData): SearchInfo {
        return SearchInfo(
            userId=searchData.userId,
            nickName = searchData.nickName,
            profileImageUrl = searchData.profileImageUrl
        )
    }
}