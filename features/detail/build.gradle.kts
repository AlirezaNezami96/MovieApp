import org.jetbrains.kotlin.kapt3.base.Kapt.kapt

plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("kotlin-parcelize")
    id ("kotlin-kapt")
    id ("dagger.hilt.android.plugin")
    id("de.mannodermaus.android-junit5") version "1.9.3.0"
}

android {
    namespace = "alireza.nezami.detail"
    compileSdk = 33

    defaultConfig {
        minSdk = 24
        targetSdk = 33

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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
    buildFeatures {
        compose = true
    }
}

dependencies {
    implementation(project(":core:model"))
    implementation(project(":core:domain"))
    implementation(project(":core:common"))
    implementation(project(":core:designSystem"))

    implementation(libs.coreKtx)
    implementation(libs.lifecycleRuntimeKtx)
    implementation(libs.viewModelCompose)
    implementation(libs.viewModel)
    implementation(libs.composeBom)
    implementation(libs.ui)
    implementation(libs.navigation)
    implementation(libs.uiGraphics)
    implementation(libs.toolingPreview)
    implementation(libs.material3)
    implementation(libs.timber)
    implementation(libs.constraintlayout)
    implementation(libs.hilt.android)
    implementation(libs.androidx.hilt.navigation.compose)
    implementation(libs.kotlinx.coroutines.android)
    kapt(libs.hilt.compiler)

    val mock_version = "1.13.7"
    val junit5_version = "5.9.3"

    testImplementation("org.junit.jupiter:junit-jupiter-api:$junit5_version")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:$junit5_version")
    testImplementation("org.junit.jupiter:junit-jupiter-params:$junit5_version")

    val turbine_version = "1.0.0"
    testImplementation ("app.cash.turbine:turbine:$turbine_version")

    val coroutines_version = "1.7.3"
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:$coroutines_version")

    testImplementation("io.mockk:mockk:${mock_version}")
    testImplementation("io.mockk:mockk-android:${mock_version}")
    testImplementation("io.mockk:mockk-agent:${mock_version}")

}