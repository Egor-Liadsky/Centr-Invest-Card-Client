plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.turtleteam.impl"
    compileSdk = 34

    defaultConfig {
        minSdk = 26

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.3"
    }
    buildTypes {
        release {
            isMinifyEnabled = false
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
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {

    implementation(project(":feature:speaker:api"))
    implementation(project(":feature:account:api"))
    implementation(project(":feature:home:api"))
    implementation(project(Modules.core_navigation))
    implementation(project(Modules.core_view))
    implementation(project(Modules.core_data))
    implementation(project(Modules.settings))

    debugImplementation("androidx.compose.ui:ui-tooling:1.5.2")
    implementation("androidx.compose.ui:ui-tooling-preview:1.5.2")

    implementation("com.google.guava:guava:32.1.3-jre")

    implementation("me.onebone:toolbar-compose:2.3.5")
    implementation(Dependencies.Data.ktorCore)
    implementation(Dependencies.Data.ktorJson)
    implementation(Dependencies.Android.androidCore)
    implementation(Dependencies.Android.appcompat)
    implementation(Dependencies.JetpackCompose.material)
    implementation(Dependencies.JetpackCompose.activityCompose)
    implementation(Dependencies.JetpackCompose.ui)
    implementation(Dependencies.JetpackCompose.navigation)
    implementation(Dependencies.DI.koin)
}