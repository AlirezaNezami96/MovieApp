plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id ("kotlin-kapt")
    id ("dagger.hilt.android.plugin")
}

android {
    namespace = "alireza.nezami.movieapp"
    compileSdk = 33

    defaultConfig {
        applicationId = "alireza.nezami.movieapp"
        minSdk = 24
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables.useSupportLibrary = true
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
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

    composeOptions {
        kotlinCompilerExtensionVersion = "1.3.2"
    }

    packagingOptions {
        resources {
            excludes.add("/META-INF/{AL2.0,LGPL2.1}")
        }
    }
}

dependencies {
    implementation(project(":features:home"))
    implementation(project(":core:data"))
    implementation(project(":core:designSystem"))

    implementation(libs.coreKtx)
    implementation(platform(libs.kotlinBom))
    implementation(libs.lifecycleRuntimeKtx)
    implementation(libs.viewModelCompose)
    implementation(libs.activityCompose)
    implementation(libs.composeBom)
    implementation(libs.ui)
    implementation(libs.navigation)
    implementation(libs.uiGraphics)
    implementation(libs.toolingPreview)
    implementation(libs.material3)
    implementation(libs.hilt.android)
    implementation(libs.androidx.hilt.navigation.compose)
    implementation(libs.kotlinx.coroutines.android)
    kapt(libs.hilt.compiler)


}
