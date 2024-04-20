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
    implementation(project(":feature:home:api"))
    implementation(project(":feature:account:api"))
    implementation(project(":feature:assistant:api"))
    implementation(project(":feature:payment:api"))
    implementation(project(":feature:detail_card:api"))
    implementation(project(":feature:settings:api"))
    implementation(project(Modules.core_view))
    implementation(project(Modules.core_navigation))
    implementation(project(Modules.core_data))

    implementation("me.onebone:toolbar-compose:2.3.5")
    implementation(Dependencies.Data.ktorJson)
    implementation(Dependencies.Data.ktorCore)
    implementation(Dependencies.Android.androidCore)
    implementation(Dependencies.Android.appcompat)
    implementation(Dependencies.JetpackCompose.material)
    implementation("androidx.compose.material:material:1.6.5")
    implementation(Dependencies.JetpackCompose.activityCompose)
    implementation(Dependencies.JetpackCompose.ui)
    implementation(Dependencies.JetpackCompose.navigation)
    implementation(Dependencies.DI.koin)
}