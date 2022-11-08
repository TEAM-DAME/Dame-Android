package com.yangbong.core_ui.util

import com.yangbong.damedame.navigator.MainNavigator
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

sealed interface Injector {
    @EntryPoint
    @InstallIn(ActivityComponent::class)
    interface MainNavigatorInjector {
        fun mainNavigator(): MainNavigator
    }
}