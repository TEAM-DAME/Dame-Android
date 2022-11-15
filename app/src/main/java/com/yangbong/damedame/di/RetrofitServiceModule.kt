package com.yangbong.damedame.di

import com.yangbong.damedame.di.annotations.DameDameServer
import com.yangbong.data.remote.service.CheckDuplicateProfileIdService
import com.yangbong.data.remote.service.LoginService
import com.yangbong.data.remote.service.SetProfileService
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
    fun providesLoginService(@DameDameServer retrofit: Retrofit): LoginService =
        retrofit.create(LoginService::class.java)

    @Provides
    @Singleton
    fun providesCheckDuplicateProfileIdService(@DameDameServer retrofit: Retrofit): CheckDuplicateProfileIdService =
        retrofit.create(CheckDuplicateProfileIdService::class.java)

    @Provides
    @Singleton
    fun providesSetProfileService(@DameDameServer retrofit: Retrofit): SetProfileService =
        retrofit.create(SetProfileService::class.java)

}
