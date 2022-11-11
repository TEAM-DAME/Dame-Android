package com.yangbong.damedame.navigator

import android.content.Context

interface MainNavigator {
    /** MainActivity로 이동 */
    fun navigateMain(context: Context)

    /** AuthActivity로 이동 */
    fun navigateAuth(
        context: Context,
        socialToken: Pair<String, String>,
        platform: Pair<String, String>
    )

    /** SettingsActivity로 이동 */
    fun navigateSettings(context: Context)

    /** WriteDiaryActivity로 이동 */
    fun navigateWriteDiary(context: Context)

    /** MainActivity로 이동시 home화면이 나오도록 transaction 하는 로직 */
    fun transactionToHome()
}
