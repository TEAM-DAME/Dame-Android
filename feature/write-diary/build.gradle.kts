plugins {
    id("com.android.library")
    id("kotlin-parcelize")
    kotlin("android")
    id("dagger.hilt.android.plugin")
    kotlin("kapt")
}

android {
    buildFeatures {
        dataBinding = true
    }
    namespace = "com.yangbong.damedame.write_diary"
}

dependencies {
    implementation(project(":core-ui"))
    implementation(project(":domain"))
    implementation(project(":shared"))

    // Android Core
    implementation(AndroidXDependencies.coreKtx)
    implementation(AndroidXDependencies.appCompat)
    implementation(AndroidXDependencies.constraintLayout)
    implementation(AndroidXDependencies.coroutines)
    implementation(AndroidXDependencies.splashScreen)

    // Material Design
    implementation(MaterialDesignDependencies.materialDesign)

    // Dagger-Hilt
    implementation(AndroidXDependencies.hilt)
    implementation(project(mapOf("path" to ":data")))
    kapt(KaptDependencies.hiltCompiler)

    // Jetpack Fragment
    implementation(AndroidXDependencies.fragment)

    // Logger - Timber
    implementation(ThirdPartyDependencies.timber)
}
