package com.yangbong.damedame.di

import com.yangbong.data.remote.data_source.*
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

    @Binds
    @Singleton
    fun bindsRemoteMyProfileDataSource(source: RemoteMyProfileDataSourceImpl): RemoteMyProfileDataSource

    @Binds
    @Singleton
    fun bindsRemoteSetCharacterDataSource(source: RemoteSetCharacterDataSourceImpl): RemoteSetCharacterDataSource

    @Binds
    @Singleton
    fun bindsRemoteNotifications(source: RemoteNotificationDataSourceImpl): RemoteNotificationDataSource

    @Binds
    @Singleton
    fun bindsRemoteGetCharacterDataSource(source: RemoteGetCharacterDataSourceImpl): RemoteGetCharacterDataSource

    @Binds
    @Singleton
    fun bindsRemoteWriteDiaryDataSource(source: RemoteWriteWriteDiaryDataSourceImpl): RemoteWriteDiaryDataSource

    @Binds
    @Singleton
    fun bindsRemoteSentimentAnalyzeDataSource(source: RemoteSentimentAnalyzeDataSourceImpl): RemoteSentimentAnalyzeDataSource

    @Binds
    @Singleton
    fun bindsRemoteSearchSource(source:RemoteSearchSourceImpl):RemoteSearchSource
}
