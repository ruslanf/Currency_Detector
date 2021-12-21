import org.gradle.api.artifacts.dsl.DependencyHandler
import org.gradle.api.file.ConfigurableFileTree

object AppDependencies {
    // Std lib
    private const val kotlinStdLib = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:${Versions.kotlinVersion}"

    // Test libs
    private const val libJUnit = "junit:junit:${Versions.junit}"
    private const val testCore = "androidx.test:core:${Versions.testCore}"
    private const val extJUnit = "androidx.test.ext:junit:${Versions.extJunit}"
    private const val espressoCore = "androidx.test.espresso:espresso-core:${Versions.espresso}"
    private const val archCore = "androidx.arch.core:core-testing:${Versions.archCore}"
    private const val coroutinesTest =
        "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.coroutinesTest}"
    private const val mockWebServerTest =
        "com.squareup.okhttp3:mockwebserver:${Versions.mockWebServerTest}"
    private const val mockTest = "io.mockk:mockk:${Versions.mockTest}"

    // Android UI
    private const val coreKtx = "androidx.core:core-ktx:${Versions.coreKtx}"
    private const val appcompat = "androidx.appcompat:appcompat:${Versions.appcompat}"
    private const val legacySupport = "androidx.legacy:legacy-support-v4:${Versions.legacyV4}"

    // Android X
    private const val activity = "androidx.activity:activity-ktx:${Versions.activityKtx}"
    private const val fragment = "androidx.fragment:fragment-ktx:${Versions.fragmentKtx}"
    // Android X DataStore API
    private const val datastore = "androidx.datastore:datastore:${Versions.datastore}"
    private const val datastoreCore = "androidx.datastore:datastore-core:${Versions.datastore}"
    private const val datastorePreferences =
        "androidx.datastore:datastore-preferences:${Versions.datastore}"

    // Navigation UI
    private const val navigationUiFragment =
        "androidx.navigation:navigation-fragment-ktx:${Versions.navigationUI}"
    private const val navigationUiKtx =
        "androidx.navigation:navigation-ui-ktx:${Versions.navigationUI}"

    // Lifecycle
    private const val lifecycle = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycle}"
    // ViewModel
    private const val lifecycleViewModel =
        "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycleViewModel}"
    // LiveData
    private const val lifecycleLivedata =
        "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.lifecycleLivedata}"
    //
    private const val lifecycleExt = "androidx.lifecycle:lifecycle-extensions:${Versions.lifecycleExt}"
    // WorkManager
    private const val workManager = "androidx.work:work-runtime-ktx:${Versions.workVersion}"

    // Material Design
    private const val materialDesign =
        "com.google.android.material:material:${Versions.materialDesign}"
    // ConstraintLayout
    private const val constraintLayout =
        "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
    // RecyclerView
    private const val recyclerView = "androidx.recyclerview:recyclerview:${Versions.recyclerView}"
    // ViewPager 2
    private const val viewPager = "androidx.viewpager2:viewpager2:${Versions.viewPager}"

    // Kotlin features
    private const val coroutinesCore =
        "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutinesCore}"
    private const val coroutinesAndroid =
        "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutinesAndroid}"
    private const val coroutinesPlayServices =
        "org.jetbrains.kotlinx:kotlinx-coroutines-play-services:${Versions.coroutinesPlayServices}"

    // DI
    // Koin
    private const val koinCore = "io.insert-koin:koin-core:${Versions.koinVersion}"
    private const val koinAndroid = "io.insert-koin:koin-android:${Versions.koinVersion}"
//    private const val koinExtensions = "io.insert-koin:koin-android-ext:${Versions.koinVersion}"
    private const val koinWorkManager = "io.insert-koin:koin-androidx-workmanager:${Versions.koinVersion}"
    private const val koinNavigation = "io.insert-koin:koin-androidx-navigation:${Versions.koinVersion}"
    private const val koinKtor = "io.insert-koin:koin-ktor:${Versions.koinVersion}"

    // Coil
    private const val coil = "io.coil-kt:coil:${Versions.coil}"
    // Chrome Tabs
    private const val chromeTabs = "com.android.support:customtabs:${Versions.chromeTabs}"
    // ThreeTeenABP
    private const val threetenABP = "com.jakewharton.threetenabp:threetenabp:${Versions.threetenABP}"

    // Network
    // KTor
    private const val ktorClient = "io.ktor:ktor-client-core:${Versions.ktor_version}"
    private const val ktorAndroid = "io.ktor:ktor-client-android:${Versions.ktor_version}"
    private const val ktorCIO = "io.ktor:ktor-client-cio:${Versions.ktor_version}"
    private const val ktorOkHTTP = "io.ktor:ktor-client-okhttp:${Versions.ktor_version}"
    private const val ktorLogging = "io.ktor:ktor-client-logging-jvm:${Versions.ktor_version}"
    private const val serialization = "io.ktor:ktor-client-serialization:${Versions.ktor_version}"
    private const val serializationJSON = "org.jetbrains.kotlinx:kotlinx-serialization-json:${Versions.serialization}"
    private const val ktorGson = "io.ktor:ktor-client-gson:${Versions.ktor_version}"


    // Retrofit
    private const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    private const val retrofitGson = "com.squareup.retrofit2:converter-gson:${Versions.retrofitGson}"
    private const val gson = "com.google.code.gson:gson:${Versions.gson}"
    private const val okhttp = "com.squareup.okhttp3:okhttp:${Versions.okhttp}"
    private const val okhttpURLConnection = "com.squareup.okhttp3:okhttp-urlconnection:${Versions.okhttp}"
    private const val okhttpLoggingInterceptor =
        "com.squareup.okhttp3:logging-interceptor:${Versions.okhttpLoggingInterceptor}"
    private const val retrofitCoroutines =
        "com.jakewharton.retrofit:retrofit2-kotlin-coroutines-adapter:${Versions.retrofitCoroutines}"
    private const val conscrypt = "org.conscrypt:conscrypt-android:${Versions.conscrypt}"

    // Room components
    private const val roomRuntime = "androidx.room:room-runtime:${Versions.room}"
    private const val roomCompiler = "androidx.room:room-compiler:${Versions.room}" // kapt
    private const val roomKtx = "androidx.room:room-ktx:${Versions.room}"
    private const val roomTest = "androidx.room:room-testing:${Versions.room}" // androidTestImplementation

    // Play Services
//    private const val playServicesAuth = "com.google.android.gms:play-services-auth:${Versions.playServicesAuth}"
//    private const val playServicesPhone = "com.google.android.gms:play-services-auth-api-phone:${Versions.playServicesPhone}"

    // Firebase
    private const val fBoM = "com.google.firebase:firebase-bom:${Versions.fBoM}"
    // Analytics
//    private const val fAnalytics = "com.google.firebase:firebase-analytics:${Versions.fAnalytics}"
    // Crashlytics
    private const val fCrashlytics = "com.google.firebase:firebase-crashlytics:${Versions.fCrashlytics}"
    // Dynamics Links
//    private const val fDynamicLinks = "com.google.firebase:firebase-dynamic-links:${Versions.fDynamicLink}"
//    private const val fMessaging = "com.google.firebase:firebase-messaging:${Versions.fMessaging}"
//    private const val fAuth = "com.google.firebase:firebase-auth:${Versions.fAuth}"
//    private const val fServicesAuth = "com.google.android.gms:play-services-auth:${Versions.fServicesAuth}"

    // HMS
//    private const val hmsCore = "com.huawei.agconnect:agconnect-core:${Versions.hmsCore}"

    // Desugaring API
    private const val deSugaringApi = "com.android.tools:desugar_jdk_libs:${Versions.desugaringApi}"

    // Yandex MapKit
    // Облегчённая библиотека, содержит только карту, слой пробок,
    // LocationManager, UserLocationLayer и возможность скачивать оффлайн карты (только в платной версии).
    private const val yandexMapkit = "com.yandex.android:maps.mobile:${Versions.yandexMapkit}"

    val appLibraries = listOf(
        kotlinStdLib,
        coreKtx,
        appcompat,
        legacySupport
    )

    val kotlinLibraries = listOf(
        coroutinesCore,
        coroutinesAndroid,
        coroutinesPlayServices
    )

    val androidTestLibraries = listOf(
        extJUnit,
        espressoCore,
        roomTest
    )

    val testLibraries = listOf(
        libJUnit,
        testCore,
        archCore,
        coroutinesTest,
        mockWebServerTest,
        mockTest
    )

    val kaptLibraries = listOf(
        roomCompiler
    )

    val androidXLibraries = listOf(
        activity,
        fragment,
        datastore,
        datastoreCore,
        datastorePreferences,
        navigationUiFragment,
        navigationUiKtx,
        lifecycle,
        lifecycleViewModel,
        lifecycleLivedata,
        lifecycleExt,
        workManager/*,*/
//        playServicesAuth,
//        playServicesPhone
    )

    val uiLibraries = listOf(
        materialDesign,
        constraintLayout,
        recyclerView,
        viewPager,
        chromeTabs
    )

    val moduleLibraries = listOf(
        koinCore,
        koinAndroid,
//        koinExtensions,
        koinWorkManager,
        koinNavigation,
        koinKtor,
        coil,
        threetenABP,
        yandexMapkit
    )

    val ktorLibraries = listOf(
        ktorClient,
        ktorAndroid,
        ktorCIO,
        ktorOkHTTP,
        ktorLogging,
        serialization,
        serializationJSON,
        gson,
        ktorGson
    )

    val room = listOf(
        roomRuntime,
        roomKtx
    )

    val firebase = listOf(
        fBoM,
        /*fAnalytics,*/
        fCrashlytics/*,
        fDynamicLinks,
        fMessaging,
        fAuth,
        fServicesAuth,
        billingClient,
        billingKtx*/
    )

//    val hms = listOf(hmsCore)

    val deSugaring = listOf(deSugaringApi)
}

// Util functions for adding the different type dependencies from build.gradle.kts.kts file
fun DependencyHandler.kapt(list: List<String>) {
    list.forEach { dependency ->
        add("kapt", dependency)
    }
}

fun DependencyHandler.implementation(list: ConfigurableFileTree) {
    list.forEach { dependency ->
//        add("implementation", dependency)
    }
}

fun DependencyHandler.implementation(list: List<String>) {
    list.forEach { dependency ->
        add("implementation", dependency)
    }
}

fun DependencyHandler.implementation(list: String) {
    list.forEach { dependency ->
        add("implementation", dependency)
    }
}

fun DependencyHandler.androidTestImplementation(list: String) {
    list.forEach { dependency ->
        add("androidTestImplementation", dependency)
    }
}

fun DependencyHandler.androidTestImplementation(list: List<String>) {
    list.forEach { dependency ->
        add("androidTestImplementation", dependency)
    }
}

fun DependencyHandler.testImplementation(list: String) {
    list.forEach { dependency ->
        add("testImplementation", dependency)
    }
}

fun DependencyHandler.testImplementation(list: List<String>) {
    list.forEach { dependency ->
        add("testImplementation", dependency)
    }
}

fun DependencyHandler.coreLibraryDesugaring(list: List<String>) {
    list.forEach { dependency ->
        add("coreLibraryDesugaring", dependency)
    }
}