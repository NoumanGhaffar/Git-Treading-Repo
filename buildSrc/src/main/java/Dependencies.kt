object Dependencies {
    object Version {

        const val recyclerView = "1.1.0"
        const val kotlin = "1.4.10"
        const val ktx = "1.3.2"
        const val constraintLayout = "2.0.1"
        const val junit = "4.12"
        const val dagger = "2.24"
        const val androidX = "1.1.0"
        const val lifecycle = "2.1.0"
        const val retrofit = "2.6.1"
        const val okhttp = "4.6.0"
        const val conscrypt = "2.2.1"
        const val coroutine = "1.3.1"
        const val sdp = "1.0.6"
        const val glide = "4.8.0"
        const val lottieVersion = "3.4.0"
        const val espresso = "3.1.0"
        const val testingCore = "2.1.0"
        const val testExt = "1.1.2"
        const val room = "2.1.0"
        const val mokitoInline = "4.0.0"
        const val mokitoKotlin = "2.2.0"
        const val liveDataTest = "1.2.0"
        const val mockk = "1.9.3"
    }

    // kotlin
    const val kotlin = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Version.kotlin}"
    const val ktx = "androidx.core:core-ktx:${Version.ktx}"

    // UI
    const val constraintLayout = "androidx.constraintlayout:constraintlayout:${Version.constraintLayout}"
    const val sdp = "com.intuit.sdp:sdp-android:${Version.sdp}"
    const val glide = "com.github.bumptech.glide:glide:${Version.glide}"
    const val glideAnnotation = "com.github.bumptech.glide:compiler:${Version.glide}"
    const val recyclerView = "androidx.recyclerview:recyclerview:${Version.recyclerView}"
    const val shimmer = "com.facebook.shimmer:shimmer:0.5.0"
    const val lottieAnimation = "com.airbnb.android:lottie:${Version.lottieVersion}"


    // dependency injection
    const val dagger = "com.google.dagger:dagger:${Version.dagger}"
    const val daggerCompiler = "com.google.dagger:dagger-compiler:${Version.dagger}"
    const val daggerAndroid = "com.google.dagger:dagger-android:${Version.dagger}"
    const val daggerAndroidSupport = "com.google.dagger:dagger-android-support:${Version.dagger}"
    const val daggerAndroidProcessor =
        "com.google.dagger:dagger-android-processor:${Version.dagger}"

    // network
    const val retrofit = "com.squareup.retrofit2:retrofit:${Version.retrofit}"
    const val retrofitConverter = "com.squareup.retrofit2:converter-gson:${Version.retrofit}"
    const val loggingInterceptor = "com.squareup.okhttp3:logging-interceptor:${Version.okhttp}"
    const val conscrypt = "org.conscrypt:conscrypt-android:${Version.conscrypt}"

    // test
    const val junit = "junit:junit:${Version.junit}"
    const val coroutineTest = "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Version.coroutine}"
    const val espressoTest = "androidx.test.espresso:espresso-core:${Version.espresso}"
    const val testExt = "androidx.test.ext:junit:${Version.testExt}"
    const val androidCoreTesting = "androidx.arch.core:core-testing:${Version.testingCore}"
    const val mockitoInline = "org.mockito:mockito-inline:${Version.mokitoInline}"
    const val mockitoKotlin = "com.nhaarman.mockitokotlin2:mockito-kotlin:${Version.mokitoKotlin}"
    const val liveDataTesting = "com.jraska.livedata:testing-ktx:${Version.liveDataTest}"
    const val mockk = "io.mockk:mockk:${Version.mockk}"

    // coroutine
    const val coroutineCore = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Version.coroutine}"
    const val coroutineAndroid = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Version.coroutine}"

    // lifecycle
    const val viewmodelKtx = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Version.lifecycle}"
    const val activityKtx = "androidx.activity:activity-ktx:${Version.androidX}"

    // db
    const val roomRuntime = "androidx.room:room-runtime:${Version.room}"
    const val roomCompiler = "androidx.room:room-compiler:${Version.room}"
    const val roomKtx = "androidx.room:room-ktx:${Version.room}"

}
