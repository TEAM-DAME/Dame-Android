package com.yangbong.damedame.di

<<<<<<< HEAD
=======
import com.yangbong.data.repository.LoginRepositoryImpl
import com.yangbong.data.repository.MyProfileRepositoryImpl
import com.yangbong.data.repository.NotificationRepositoryImpl
import com.yangbong.data.repository.SetProfileRepositoryImpl
import com.yangbong.domain.repository.LoginRepository
import com.yangbong.domain.repository.MyProfileRepository
import com.yangbong.domain.repository.NotificationRepository
import com.yangbong.domain.repository.SetProfileRepository
>>>>>>> 023adb75b16e98bf93e70b97e03b5e19871d2f53
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
<<<<<<< HEAD
    fun bindsWriteDiaryRepository(repository:WriteDiaryRepositoryImpl):WriteDiaryRepository

    @Binds
    @Singleton
    fun bindsSentimentAnalyzeRepository(repository: SentimentAnalyzeRepositoryImpl):SentimentAnalyzeRepository
=======
    fun bindsHomeRepository(repository: HomeRepositoryImpl): HomeRepository
>>>>>>> 023adb75b16e98bf93e70b97e03b5e19871d2f53
}
