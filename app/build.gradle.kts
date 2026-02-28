plugins {
    id("com.android.application") version "8.4.1"
    id("org.jetbrains.kotlin.android") version "1.9.24"
    id("org.jetbrains.kotlin.plugin.compose") version "1.9.24"
    id("com.google.devtools.ksp") version "1.9.24-1.0.20"
    id("org.jetbrains.kotlin.plugin.parcelize")
}

android {
    namespace = "com.example.oramenteevendas"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.oramenteevendas"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlinOptions {
        jvmTarget = "11"
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.composeBom.get()
    }
}

kotlin {
    jvmToolchain(17)
}

dependencies {
    implementation(libs.androidx-core-ktx)
    implementation(libs.androidx-lifecycle-runtime-ktx)
    implementation(libs.androidx-activity-compose)

    implementation(platform(libs.androidx-compose-bom))
    implementation(libs.androidx-compose-ui)
    implementation(libs.androidx-compose-ui-graphics)
    implementation(libs.androidx-compose-ui-tooling-preview)
    implementation(libs.androidx-compose-material3)

    implementation("androidx.navigation:navigation-compose:2.7.7")
    implementation("androidx.lifecycle:lifecycle-runtime-compose:2.7.0")

    // ROOM
    implementation("androidx.room:room-runtime:2.6.1")
    implementation("androidx.room:room-ktx:2.6.1")
    ksp("androidx.room:room-compiler:2.6.1")

    // Testes
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx-junit)
    androidTestImplementation(libs.androidx-espresso-core)
    androidTestImplementation(platform(libs.androidx-compose-bom))
    androidTestImplementation(libs.androidx-compose-ui-test-junit4)
    debugImplementation(libs.androidx-compose-ui-tooling)
    debugImplementation(libs.androidx-compose-ui-test-manifest)
}