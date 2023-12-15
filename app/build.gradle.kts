plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
}

android {
    namespace = "com.example.bonusapp"
    compileSdk = 33

    defaultConfig {
        applicationId = "com.example.bonusapp"
        minSdk = 21
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"
        buildFeatures{
            viewBinding = true
            dataBinding = true
        }
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}
dependencies {

    implementation( "androidx.room:room-runtime:2.3.0")
    kapt("androidx.room:room-compiler:2.3.0")
    implementation("io.github.cymchad:BaseRecyclerViewAdapterHelper4:4.1.2")
// 支持rxjava2
    implementation("androidx.room:room-rxjava2:2.3.0")

// 支持rxjava3
    implementation("androidx.room:room-rxjava3:2.3.0")
    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.8.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    implementation("org.greenrobot:eventbus:3.3.1")
}