package com.yangbong.damedame.di

import com.yangbong.core_data.fcm.FirebaseTokenManager
import com.yangbong.damedame.fcm.FirebaseTokenManagerImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface FirebaseTokenManagerModule {

    @Binds
    fun bindsFirebaseTokenManager(fireBaseTokenManger: FirebaseTokenManagerImpl): FirebaseTokenManager
}
