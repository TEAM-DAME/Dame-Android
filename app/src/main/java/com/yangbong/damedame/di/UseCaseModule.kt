package com.yangbong.damedame.di

import com.yangbong.domain.repository.LoginRepository
import com.yangbong.domain.use_case.login.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object UseCaseModule {

    @ViewModelScoped
    @Provides
    fun provideLoginUseCases(repository: LoginRepository): LoginUseCases {
        return LoginUseCases(
            getFcmToken = GetFcmToken(repository),
            getAccessToken = GetAccessToken(repository),
            saveAccessToken = SaveAccessToken(repository),
            getIsFirstVisited = GetIsFirstVisited(repository),
            setIsFirstVisited = SetIsFirstVisited(repository),
            saveUserNickname = SaveUserNickname(repository),
            saveUserProfileImageUrl = SaveUserProfileImageUrl(repository),
            postLogin = PostLogin(repository)
        )
    }

}