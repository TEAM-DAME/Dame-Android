package com.yangbong.damedame.di

import com.yangbong.data.remote.data_source.RemoteLoginDataSource
import com.yangbong.data.remote.data_source.RemoteLoginDataSourceImpl
import com.yangbong.data.remote.data_source.RemoteSetProfileDataSource
import com.yangbong.data.remote.data_source.RemoteSetProfileDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RemoteDataSourceModule {

    @Binds
    @Singleton
    fun bindsRemoteLoginDataSource(source: RemoteLoginDataSourceImpl): RemoteLoginDataSource

    @Binds
    @Singleton
    fun bindsRemoteCheckDuplicateProfileIdDataSource(source: RemoteSetProfileDataSourceImpl): RemoteSetProfileDataSource



}
