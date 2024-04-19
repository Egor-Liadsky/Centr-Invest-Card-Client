object Versions {

    object DI {
        const val koin = "3.5.0"
    }

    object Network {
        const val ktor = "2.2.2"
    }

    object Kotlin {
        const val kotlinxSerialization = "1.6.0"
        const val kotlinxCoroutinesCore = "1.7.3"
    }

    object Android {
        const val androidCore = "1.8.0"
        const val lifecycle = "2.6.2"
        const val appcompat = "1.6.1"
    }

    object JetpackCompose {
        const val ui = "1.5.3"
        const val activityCompose = "1.5.3"
        const val material = "1.1.2"
        const val toolingPreview = "1.5.0-beta01"
        const val navigation = "0.33.2-alpha"
    }
}

object Dependencies {

    object DI {
        val koin by lazy { "io.insert-koin:koin-androidx-compose:${Versions.DI.koin}" }
        val koinAndroid by lazy { "io.insert-koin:koin-android:${Versions.DI.koin}" }
    }

    object Kotlin {
        val kotlinxSerialization by lazy { "org.jetbrains.kotlinx:kotlinx-serialization-json:${Versions.Kotlin.kotlinxSerialization}" }
    }

    object Android {
        val androidCore by lazy { "androidx.core:core-ktx:${Versions.Android.androidCore}" }
        val lifecycle by lazy { "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.Android.lifecycle}" }
        val appcompat by lazy { "androidx.appcompat:appcompat:${Versions.Android.appcompat}" }
    }

    object JetpackCompose {
        val ui by lazy { "androidx.compose.ui:ui:${Versions.JetpackCompose.ui}" }
        val uiGraphics by lazy { "androidx.compose.ui:ui:${Versions.JetpackCompose.ui}" }
        val uiToolingPreview by lazy { "androidx.compose.ui:ui-tooling-preview-android:1.5.0-beta01" }
        val foundation by lazy { "androidx.compose.foundation:foundation:${Versions.JetpackCompose.ui}" }
        val material by lazy { "androidx.compose.material3:material3:${Versions.JetpackCompose.material}" }
        val activityCompose by lazy { "androidx.activity:activity-compose:${Versions.JetpackCompose.activityCompose}" }
        val runtime by lazy { "androidx.compose.runtime:runtime:${Versions.JetpackCompose.ui}" }
        val navigation by lazy { "com.google.accompanist:accompanist-navigation-animation:${Versions.JetpackCompose.navigation}" }
    }

    object Data {
        val ktorCore by lazy { "io.ktor:ktor-client-core:${Versions.Network.ktor}" }
        val ktorLogging by lazy { "io.ktor:ktor-client-logging:${Versions.Network.ktor}" }
        val ktorContentNegotiation by lazy { "io.ktor:ktor-server-content-negotiation:${Versions.Network.ktor}" }
        val ktorJson by lazy { "io.ktor:ktor-serialization-kotlinx-json:${Versions.Network.ktor}" }
        val ktorClient by lazy { "io.ktor:ktor-client-okhttp:2.2.2" }

    }

    object Test {
        val junitTest by lazy { "junit:junit:4.13.2" }
        val junitAndroidTest by lazy { "androidx.test.ext:junit:1.1.5" }
    }
}
