package com.yangbong.damedame.di

import com.yangbong.damedame.di.annotations.DameDameServer
import com.yangbong.damedame.di.annotations.NaverClovaSentimentServer
import com.yangbong.data.remote.service.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RetrofitServiceModule {

    @Provides
    @Singleton
    fun providesNaverClovaSentimentService(@NaverClovaSentimentServer retrofit: Retrofit): NaverClovaSentimentService =
        retrofit.create(NaverClovaSentimentService::class.java)

    @Provides
    @Singleton
    fun providesLoginService(@DameDameServer retrofit: Retrofit): LoginService =
        retrofit.create(LoginService::class.java)

    @Provides
    @Singleton
    fun providesCheckDuplicateProfileIdService(@DameDameServer retrofit: Retrofit): CheckDuplicateProfileIdService =
        retrofit.create(CheckDuplicateProfileIdService::class.java)

    @Provides
    @Singleton
    fun providesSetProfileService(@DameDameServer retrofit: Retrofit): SignUpService =
        retrofit.create(SignUpService::class.java)

    @Provides
    @Singleton
    fun provideMyProfileService(@DameDameServer retrofit: Retrofit): MyProfileService =
        retrofit.create(MyProfileService::class.java)
}
