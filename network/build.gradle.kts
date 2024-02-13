plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    `maven-publish`
    id("maven-publish")
}

android {
    namespace = "com.devrev.network"
    compileSdk = 34

    defaultConfig {
    //    applicationId = "com.devrev.network"
        minSdk = 24
        targetSdk = 34
//        versionCode = 1
//        versionName = "1.0"

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
        jvmTarget = "17"
    }
}

afterEvaluate {
    publishing {
        publications {
            create<MavenPublication>("release") {
                from(components["release"])
                groupId = "com.devrev.network"
                artifactId = "com.devrev.network"
                version = "1.0.2"
                // Add any additional configuration for your publication here
            }
        }
    }
}

dependencies {

    api("androidx.core:core-ktx:1.10.1")
    api("androidx.appcompat:appcompat:1.6.1")
    api("com.google.android.material:material:1.9.0")
    testApi("junit:junit:4.13.2")
    androidTestApi("androidx.test.ext:junit:1.1.5")
    androidTestApi("androidx.test.espresso:espresso-core:3.5.1")

    // Coroutines
    api("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.1")
    api("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.1")

    // Koin
    api("io.insert-koin:koin-android:3.4.2")


    // Retrofit
    api("com.squareup.retrofit2:retrofit:2.9.0")
    api("com.squareup.retrofit2:converter-gson:2.9.0")
    api("com.squareup.okhttp3:logging-interceptor:5.0.0-alpha.2")


    // Glide
    api("com.github.bumptech.glide:glide:4.15.1")
    annotationProcessor("com.github.bumptech.glide:compiler:4.12.0")


    // Lifecycle ViewModel
    api("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.1")

    //kotlin navigation components
    api("androidx.navigation:navigation-fragment-ktx:2.6.0")
    api("androidx.navigation:navigation-ui-ktx:2.6.0")
}