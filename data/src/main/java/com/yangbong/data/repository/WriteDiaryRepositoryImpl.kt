package com.yangbong.data.repository

import com.yangbong.data.local.data_source.LocalPreferenceUserDataSource
import com.yangbong.data.remote.data_source.RemoteWriteDiarySource
import com.yangbong.domain.entity.request.DomainWriteDiaryRequest
import com.yangbong.domain.repository.WriteDiaryRepository
import javax.inject.Inject

class WriteDiaryRepositoryImpl @Inject constructor(
    private val writeDiaryDataSource:RemoteWriteDiarySource,
    private val localPreferenceUserDataSource: LocalPreferenceUserDataSource
) :WriteDiaryRepository{
    override fun getUserId(): Int {
        TODO("Not yet implemented")
    }

    override suspend fun postWriteDiary(WriteRequest: DomainWriteDiaryRequest): Result<String> {
        TODO("Not yet implemented")
    }
}