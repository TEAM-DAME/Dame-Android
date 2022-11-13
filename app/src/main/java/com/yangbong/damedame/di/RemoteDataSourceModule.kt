package com.yangbong.damedame.di

import com.yangbong.data.remote.data_source.RemoteLoginDataSource
import com.yangbong.data.remote.data_source.RemoteLoginDataSourceImpl
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
}
