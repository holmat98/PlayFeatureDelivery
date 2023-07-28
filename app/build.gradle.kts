plugins {
    id ("com.android.application")
    id ("org.jetbrains.kotlin.android")
    kotlin ("kapt")
    id ("com.google.dagger.hilt.android")

}

android {
    namespace = "com.example.dynamicdelivery"
    compileSdk = 33

    defaultConfig {
        applicationId = "com.example.dynamicdelivery"
        minSdk = 30
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    // Use this code if you want to include all language packages when the app is installed from Google Play Store
    /*bundle {
        language {
            enableSplit = false
        }
    }*/

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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
        viewBinding = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.5"
    }
    packagingOptions {
        resources {
            excludes.add("/META-INF/{AL2.0,LGPL2.1}")
        }
    }
    dynamicFeatures += setOf(
        ":dynamicfeature1",
        ":installtimefeaturemodule",
        ":conditionalfeaturemodule",
        ":dynamicfeature4"
    )
}

dependencies {

    implementation("androidx.core:core-ktx:1.10.1")
    implementation ("androidx.lifecycle:lifecycle-runtime-ktx:2.6.1")
    implementation ("androidx.activity:activity-compose:1.7.2")
    implementation ("androidx.compose.ui:ui:1.4.3")
    implementation ("androidx.compose.ui:ui-graphics:1.4.3")
    implementation ("androidx.compose.ui:ui-tooling-preview:1.4.3")
    implementation ("androidx.compose.material3:material3:1.2.0-alpha02")
    implementation ("androidx.navigation:navigation-compose:2.6.0")

    // Hilt
    implementation("com.google.dagger:hilt-android:2.44")
    implementation("androidx.hilt:hilt-navigation-compose:1.0.0")
    kapt("com.google.dagger:hilt-android-compiler:2.44")

    // Navigation
    implementation("androidx.navigation:navigation-fragment-ktx:2.5.3")
    implementation("androidx.navigation:navigation-ui-ktx:2.5.3")
    implementation("androidx.navigation:navigation-dynamic-features-fragment:2.5.3")

    // Activity
    implementation("androidx.activity:activity-ktx:1.7.2")

    // Material design
    implementation("com.google.android.material:material:1.9.0")

    // PlayCore
    implementation("com.google.android.play:feature-delivery:2.1.0")
    implementation("com.google.android.play:feature-delivery-ktx:2.1.0")
}