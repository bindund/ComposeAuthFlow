plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    id("org.jetbrains.kotlin.kapt")
}

android {
    namespace = "com.demo.core.data"
    compileSdk = 34

    defaultConfig {
        minSdk = 24

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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
}

dependencies {
    implementation(libs.bundles.koin)
    implementation(libs.ktor.client.core)
    implementation(libs.ktor.client.cio)
    implementation(libs.ktor.client.logging)
    implementation(libs.ktor.client.content.negotiation)
    implementation(libs.ktor.serialization.kotlinx.json)
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.timber)

    implementation("androidx.room:room-runtime:2.6.1")
    implementation("androidx.room:room-ktx:2.6.1")
    kapt("androidx.room:room-compiler:2.6.1")
//    implementation("io.ktor:ktor-client-core:2.3.8")
//    implementation("io.ktor:ktor-client-cio:2.3.8")
//    implementation("io.ktor:ktor-client-content-negotiation:2.3.8")
//    implementation("io.ktor:ktor-serialization-kotlinx-json:2.3.8")
//
//    implementation("io.ktor:ktor-client-core:2.3.8")
//    implementation("io.ktor:ktor-client-cio:2.3.8")
//    implementation("io.ktor:ktor-client-content-negotiation:2.3.8")
//    implementation("io.ktor:ktor-serialization-kotlinx-json:2.3.8")
//    implementation("io.ktor:ktor-client-logging:2.3.8")
//
//    implementation("io.ktor:ktor-client-core-jvm:2.3.8")
//    implementation("io.ktor:ktor-client:2.3.8")
//    implementation("io.ktor:ktor-client-plugins:2.3.8")
//    implementation("io.insert-koin:koin-core:3.5.3")
//    implementation("io.insert-koin:koin-android:3.5.3")

    implementation(projects.core.domain)
    implementation(projects.core.database)
}