package com.yangbong.damedame.notification

import android.content.Intent

object NavigatorIntentObject {
    private lateinit var mainIntent: Intent

    fun setMainIntent(intent: Intent) {
        this.mainIntent = intent
    }

    fun getMainIntent(): Intent = mainIntent
}