plugins {
    id("com.android.library")
    kotlin("android")
    id("dagger.hilt.android.plugin")
    kotlin("kapt")
}

android {
    buildFeatures {
        dataBinding = true
    }
    namespace = "com.yangbong.damedame.core_ui"
}

dependencies {
    implementation(project(":shared"))
    implementation(project(":navigator"))

    implementation(ThirdPartyDependencies.timber)

    // Android Core
    implementation(AndroidXDependencies.coreKtx)
    implementation(AndroidXDependencies.appCompat)
    implementation(AndroidXDependencies.constraintLayout)
    implementation(AndroidXDependencies.legacy)
    implementation(AndroidXDependencies.coroutines)
    implementation(AndroidXDependencies.fragment)
    coreLibraryDesugaring(AndroidXDependencies.desugarLibrary)

    // Dagger-Hilt
    implementation(AndroidXDependencies.hilt)
    kapt(KaptDependencies.hiltCompiler)

    // ThirdParty Library
    implementation(ThirdPartyDependencies.lottie)

    // Material Design
    implementation(MaterialDesignDependencies.materialDesign)

    // ImageLoading Library
    implementation(ThirdPartyDependencies.coil)
    implementation(ThirdPartyDependencies.glide)

    // RxBinding
    implementation(ThirdPartyDependencies.rxBinding)

    // RxKotlin
    implementation(ThirdPartyDependencies.rxKotlin)

}
