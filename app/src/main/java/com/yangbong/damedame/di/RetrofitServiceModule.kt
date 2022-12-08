package com.yangbong.damedame.di

import com.yangbong.damedame.di.annotations.DameDameServer
import com.yangbong.damedame.di.annotations.NaverClovaSentimentServer
import com.yangbong.data.remote.service.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.create
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
    fun providesCheckDuplicateProfileIdService(@DameDameServer retrofit: Retrofit): CheckDuplicateProfileNicknameService =
        retrofit.create(CheckDuplicateProfileNicknameService::class.java)

    @Provides
    @Singleton
    fun providesSetProfileService(@DameDameServer retrofit: Retrofit): SignUpService =
        retrofit.create(SignUpService::class.java)

    @Provides
    @Singleton
    fun provideMyProfileService(@DameDameServer retrofit: Retrofit): MyProfileService =
        retrofit.create(MyProfileService::class.java)

    @Provides
    @Singleton
    fun provideNotificationService(@DameDameServer retrofit: Retrofit): NotificationService =
        retrofit.create(NotificationService::class.java)

    @Provides
    @Singleton
    fun provideSetCharacterService(@DameDameServer retrofit: Retrofit): SetCharacterService =
        retrofit.create(SetCharacterService::class.java)

    @Provides
    @Singleton
    fun provideGetCharacterService(@DameDameServer retrofit: Retrofit): GetCharacterService =
        retrofit.create(GetCharacterService::class.java)

    @Provides
    @Singleton
    fun provideWriteDiaryService(@DameDameServer retrofit: Retrofit): WriteDiaryService =
        retrofit.create(WriteDiaryService::class.java)

    @Provides
    @Singleton
    fun provideSearchService(@DameDameServer retrofit: Retrofit): SearchService =
        retrofit.create(SearchService::class.java)

    @Provides
    @Singleton
    fun provideUserProfileService(@DameDameServer retrofit: Retrofit): UserProfileService =
        retrofit.create(UserProfileService::class.java)

    @Provides
    @Singleton
    fun provideGetDiaryService(@DameDameServer retrofit: Retrofit): GetDiaryService =
        retrofit.create(GetDiaryService::class.java)

    @Provides
    @Singleton
    fun providePocketService(@DameDameServer retrofit: Retrofit): PocketService =
        retrofit.create(PocketService::class.java)

    @Provides
    @Singleton
    fun provideFriendsService(@DameDameServer retrofit: Retrofit): FriendsService =
        retrofit.create(FriendsService::class.java)

    @Provides
    @Singleton
    fun provideDiaryContentService(@DameDameServer retrofit: Retrofit): DiaryContentService =
        retrofit.create(DiaryContentService::class.java)
}
