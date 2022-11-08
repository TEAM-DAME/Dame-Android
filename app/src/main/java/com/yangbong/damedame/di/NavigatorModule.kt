package com.yangbong.damedame.di

import com.yangbong.damedame.navigator.MainNavigator
import com.yangbong.damedame.navigator.MainNavigatorImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
internal abstract class NavigatorModule {
    @Binds
    abstract fun provideMainNavigator(
        navigator: MainNavigatorImpl
    ): MainNavigator
}
