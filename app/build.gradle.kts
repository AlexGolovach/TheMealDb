plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")
    id("androidx.navigation.safeargs.kotlin")
}

android {
    compileSdkVersion(AndroidConfig.compileSdkVersion)
    defaultConfig {
        minSdkVersion(AndroidConfig.minSdkVersion)
        targetSdkVersion(AndroidConfig.targetSdkVersion)
        versionCode = 1
        versionName = "1.0.0"
        buildConfigField(
            "String",
            "API_URL",
            "\"https://www.themealdb.com/api/json/v1/1/\""
        )
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    lintOptions {
        isWarningsAsErrors = true
    }
    viewBinding {
        isEnabled = true
    }
    testOptions {
        execution = "ANDROIDX_TEST_ORCHESTRATOR"
        animationsDisabled = true
        unitTests.isIncludeAndroidResources = true
    }
}

dependencies {
    implementation(project(":areas"))
    implementation(project(":categories"))
    implementation(project(":ingredients"))
    implementation(project(":meals"))
    implementation(project(":domain"))
    implementation(project(":data"))
    implementation(project(":inject"))
    implementation(project(":ui"))
    implementation(Deps.Androidx.activity)
    implementation(Deps.Androidx.appcompat)
    implementation(Deps.Androidx.core)
    implementation(Deps.Androidx.fragment)
    implementation(Deps.Androidx.navFrag)
    implementation(Deps.Androidx.material)
    implementation(Deps.Androidx.constraintlayout)
    implementation(Deps.Google.dagger)
    implementation(Deps.Google.daggerAndroid)
    kapt(Deps.Google.daggerCompiler)
    kapt(Deps.Google.daggerAndroidProcessor)

    // needs only for DI generation
    implementation(Deps.Square.retrofit)
    implementation(Deps.Database.room)

    implementation(Deps.Kotlin.coroutines)
    implementation(Deps.Kotlin.coroutinesAndroid)

    androidTestImplementation(Deps.Test.junitExt)
    androidTestImplementation(Deps.Androidx.espressoContrib)
    androidTestImplementation(Deps.Androidx.espressoCore)
    androidTestImplementation(Deps.Androidx.junitKtx)
    androidTestImplementation(Deps.Androidx.testRules)
    androidTestImplementation(Deps.Androidx.testRunner)
    androidTestUtil(Deps.Androidx.testOrchestrator)
}