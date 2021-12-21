import AppDependencies.androidTestLibraries
import AppDependencies.androidXLibraries
import AppDependencies.appLibraries
import AppDependencies.deSugaring
import AppDependencies.kaptLibraries
import AppDependencies.kotlinLibraries
import AppDependencies.ktorLibraries
import AppDependencies.moduleLibraries
import AppDependencies.room
import AppDependencies.testLibraries
import AppDependencies.uiLibraries

plugins {
    id("com.android.application")
    kotlin("android")
    id("kotlin-kapt")
    id("kotlin-parcelize")
    id("kotlin-android")
}

android {
    compileSdkVersion(AppConfig.compileSdk)
    buildToolsVersion(AppConfig.buildToolsVersion)

    defaultConfig {
        applicationId = AppConfig.applicationId
        minSdkVersion(AppConfig.minSdk)
        targetSdkVersion(AppConfig.targetSdk)
        versionCode = AppConfig.verCode
        versionName = AppConfig.verName
        testInstrumentationRunner = AppConfig.androidTestInstrumentation
        vectorDrawables.useSupportLibrary = true
    }

    applicationVariants.all {
        this.outputs
            .map { it as com.android.build.gradle.internal.api.BaseVariantOutputImpl }
            .forEach { output ->
                output.outputFileName = output.outputFileName
                    .replace("app-", "Smart-Team-")
                    .replace(".apk", "-${defaultConfig.versionName}-${this.buildType.name}.apk")
            }
    }

    signingConfigs {
        create("release") {
            SigningConfig.apply {
                keyAlias = keyAliasName
                keyPassword = keyPass
//                storeFile = file(storeFilePath)
                storePassword = storePass
            }
        }
    }

    buildTypes {
        getByName("debug") {
            // ...
            signingConfig = signingConfigs.getByName("debug")
            isDebuggable = true
        }
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            signingConfig = signingConfigs.getByName("release")
            isDebuggable = false
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8

        isCoreLibraryDesugaringEnabled = true
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }

    viewBinding {
        android.buildFeatures.viewBinding = true
    }
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.aar", "*.jar"))))
    implementation(appLibraries)
    kapt(kaptLibraries)

    testImplementation(testLibraries)
    androidTestImplementation(androidTestLibraries)

    implementation(kotlinLibraries)
    implementation(uiLibraries)
    implementation(androidXLibraries)
    implementation(moduleLibraries)
    implementation(ktorLibraries)
    implementation(room)

    // DeSugaring Java API
    coreLibraryDesugaring(deSugaring)
}