package com.yangbong.damedame.navigator

import android.content.Context

interface MainNavigator {
    /** MainActivity로 이동 */
    fun navigateMain(context: Context)

    /** SetProfileActivity로 이동 */
    fun navigateSetProfile(
        context: Context,
        platform: Pair<String, String>,
        socialToken: Pair<String, String>,
        fcmToken: Pair<String, String>
    )

    /** SetCharacterActivity로 이동 */
    fun navigateSetCharacter(context: Context)

    /** AuthActivity로 이동 */
    fun navigateAuth(context: Context)

    /** SettingActivity로 이동 */
    fun navigateSetting(context: Context)

    /** WriteDiaryActivity로 이동 */
    fun navigateWriteDiary(context: Context)

    /** MainActivity로 이동시 home화면이 나오도록 transaction 하는 로직 */
    fun transactionToHome()
}
