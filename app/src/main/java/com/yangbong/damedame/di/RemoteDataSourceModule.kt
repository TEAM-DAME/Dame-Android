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
    fun bindsRemoteSetCharacterSource(source: RemoteSetCharacterDataSourceImpl): RemoteSetCharacterDataSource
<<<<<<< HEAD
    @Binds
    @Singleton
    fun bindsRemoteWriteDiarySource(source: RemoteWriteDiarySourceImpl):RemoteWriteDiarySource
    @Binds
    @Singleton
    fun bindsRemoteSentimentAnalyzeSource(soure:RemoteSentimentAnalyzeDataSourceImpl):RemoteSentimentAnalyzeDataSource
=======

    @Binds
    @Singleton
    fun bindsRemoteNotifications(source: RemoteNotificationDataSourceImpl): RemoteNotificationDataSource

    @Binds
    @Singleton
    fun bindsRemoteCharacterSource(source: RemoteCharacterDataSourceImpl): RemoteCharacterDataSource
>>>>>>> 023adb75b16e98bf93e70b97e03b5e19871d2f53
}
