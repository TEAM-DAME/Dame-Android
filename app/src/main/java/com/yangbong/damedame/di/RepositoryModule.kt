package com.yangbong.damedame.di

import com.yangbong.data.repository.LoginRepositoryImpl
import com.yangbong.data.repository.MyProfileRepositoryImpl
import com.yangbong.data.repository.SetCharacterRepositoryImpl
import com.yangbong.data.repository.SetProfileRepositoryImpl
import com.yangbong.domain.repository.LoginRepository
import com.yangbong.domain.repository.MyProfileRepository
import com.yangbong.domain.repository.SetCharacterRepository
import com.yangbong.domain.repository.SetProfileRepository
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
}
