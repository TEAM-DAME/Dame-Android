package com.yangbong.damedame.navigator

import android.content.Context
import com.yangbong.auth.AuthActivity
import com.yangbong.core_ui.extension.navigateActivity
import com.yangbong.damedame.MainActivity
import com.yangbong.set_character.ui.SetCharacterActivity
import com.yangbong.set_profile.ui.SetProfileActivity
import com.yangbong.write_diary.WriteDiaryActivity
import javax.inject.Inject

internal class MainNavigatorImpl @Inject constructor() : MainNavigator {
    override fun navigateMain(context: Context) {
        context.navigateActivity<MainActivity>()
    }

    override fun navigateSetProfile(
        context: Context,
        platform: Pair<String, String>,
        socialToken: Pair<String, String>,
        fcmToken: Pair<String, String>
    ) {
        context.navigateActivity<SetProfileActivity>(platform, socialToken, fcmToken)
    }

    override fun navigateSetCharacter(
        context: Context,
        userId: Pair<String, Int>
    ) {
        context.navigateActivity<SetCharacterActivity>(userId)
    }

    override fun navigateAuth(context: Context) {
        context.navigateActivity<AuthActivity>()
    }

    override fun navigateSetting(context: Context) {
        // TODO ("SettingActivity 추가")
    }

    override fun navigateWriteDiary(context: Context) {
        context.navigateActivity<WriteDiaryActivity>()
    }

    override fun transactionToHome() {
        MainActivity.transactionToHome()
    }


}