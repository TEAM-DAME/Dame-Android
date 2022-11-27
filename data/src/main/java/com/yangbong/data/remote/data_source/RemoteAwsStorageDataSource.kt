package com.yangbong.data.remote.data_source

interface RemoteAwsStorageDataSource {

    suspend fun uploadImage()
}