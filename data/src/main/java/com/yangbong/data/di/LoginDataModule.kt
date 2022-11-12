package com.yangbong.data.di

import com.yangbong.data.local.datasource.LocalPreferenceUserDataSource
import com.yangbong.data.repository.LoginRepositoryImpl
import com.yangbong.domain.repository.LoginRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LoginDataModule {

    @Provides
    @Singleton
    fun provideLoginRepository(localPreferenceUserDataSource: LocalPreferenceUserDataSource): LoginRepository {
        return LoginRepositoryImpl(
            localPreferenceUserDataSource = localPreferenceUserDataSource
        )
    }
}