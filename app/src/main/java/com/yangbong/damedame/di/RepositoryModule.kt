package com.yangbong.damedame.di

import com.yangbong.data.repository.LoginRepositoryImpl
import com.yangbong.data.repository.SetProfileRepositoryImpl
import com.yangbong.domain.repository.LoginRepository
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
}
