plugins {
    id("com.android.library")
    kotlin("android")
}

android {
    namespace = "com.yangbong.damedame.core_data"
}

dependencies {
    implementation(ThirdPartyDependencies.timber)

    // Android Core
    implementation(AndroidXDependencies.coreKtx)
    implementation(AndroidXDependencies.appCompat)
    implementation(AndroidXDependencies.coroutines)
}
