plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    kotlin("plugin.serialization")
}

android {
    namespace = "com.turtleteam.impl"
    compileSdk = 33

    defaultConfig {
        minSdk = 24

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
    implementation(project(":feature:home:api"))
    implementation(project(":feature:assistant:api"))
    implementation(project(":feature:settings:api"))
    implementation(project(Modules.core_view))
    implementation(project(Modules.core_navigation))
    implementation(project(Modules.core_data))

    implementation(Dependencies.Android.androidCore)
    implementation(Dependencies.Android.appcompat)
    implementation(Dependencies.JetpackCompose.material)
    implementation(Dependencies.JetpackCompose.activityCompose)
    implementation(Dependencies.JetpackCompose.ui)
    implementation(Dependencies.JetpackCompose.navigation)
    implementation(Dependencies.DI.koin)
    implementation(Dependencies.Data.ktorCore)
    implementation(Dependencies.Data.ktorJson)
}