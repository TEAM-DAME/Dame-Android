package com.yangbong.damedame.navigator

import android.content.Context
import com.yangbong.core_ui.extension.navigateActivity
import com.yangbong.damedame.MainActivity
import com.yangbong.write_diary.WriteDiaryActivity
import javax.inject.Inject

internal class MainNavigatorImpl @Inject constructor() : MainNavigator {
    override fun navigateMain(context: Context) {
        context.navigateActivity<MainActivity>()
    }

    override fun navigateAuth(
        context: Context,
        socialToken: Pair<String, String>,
        platform: Pair<String, String>
    ) {
        TODO("Not yet implemented")
    }

    override fun navigateSettings(context: Context) {
        TODO("Not yet implemented")
    }

    override fun navigateWriteDiary(context: Context) {
        context.navigateActivity<WriteDiaryActivity>()
    }

    override fun transactionToHome() {
        MainActivity.transactionToHome()
    }


}