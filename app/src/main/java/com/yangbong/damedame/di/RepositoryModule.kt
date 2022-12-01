package com.yangbong.damedame.di

import com.yangbong.data.repository.*
import com.yangbong.domain.repository.*
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    @Singleton
    fun bindsLoginRepository(repository: LoginRepositoryImpl): LoginRepository

    @Binds
    @Singleton
    fun bindsSetProfileRepository(repository: SetProfileRepositoryImpl): SetProfileRepository

    @Binds
    @Singleton
    fun bindsMyProfileRepository(repository: MyProfileRepositoryImpl): MyProfileRepository

    @Binds
    @Singleton
    fun bindsSetCharacterRepository(repository: SetCharacterRepositoryImpl): SetCharacterRepository

    @Binds
    @Singleton
    fun bindsWriteDiaryRepository(repository:WriteDiaryRepositoryImpl):WriteDiaryRepository

    @Binds
    @Singleton
    fun bindsSentimentAnalyzeRepository(repository: SentimentAnalyzeRepositoryImpl):SentimentAnalyzeRepository
}
