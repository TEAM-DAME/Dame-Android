package com.yangbong.damedame.di

import com.yangbong.data.repository.LoginRepositoryImpl
import com.yangbong.data.repository.MyProfileRepositoryImpl
import com.yangbong.data.repository.NotificationRepositoryImpl
import com.yangbong.data.repository.SetProfileRepositoryImpl
import com.yangbong.domain.repository.LoginRepository
import com.yangbong.domain.repository.MyProfileRepository
import com.yangbong.domain.repository.NotificationRepository
import com.yangbong.domain.repository.SetProfileRepository
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
    fun bindsNotificationRepository(repository: NotificationRepositoryImpl): NotificationRepository

    @Binds
    @Singleton
    fun bindsSetCharacterRepository(repository: SetCharacterRepositoryImpl): SetCharacterRepository

    @Binds
    @Singleton
    fun bindsHomeRepository(repository: HomeRepositoryImpl): HomeRepository

    @Binds
    @Singleton
    fun bindsWriteDiaryRepository(repository: WriteDiaryRepositoryImpl): WriteDiaryRepository

    @Binds
    @Singleton
    fun bindsSearchRepository(repository: SearchRepositoryImpl): SearchRepository

    @Binds
    @Singleton
    fun bindsFriendsRepository(repository: FriendsRepositoryImpl): FriendsRepository

    @Binds
    @Singleton
    fun bindsPocketRepository(repository: PocketRepositoryImpl): PocketRepository
}
