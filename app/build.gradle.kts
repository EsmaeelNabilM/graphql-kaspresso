import org.jetbrains.kotlin.konan.properties.Properties
import java.io.FileInputStream

plugins {
    id("com.android.application")
    kotlin("android")
    id("androidx.navigation.safeargs.kotlin")
    id("com.apollographql.apollo").version("2.5.2")
}

// retrieve Api keys.
val keystoreProperties = rootProject.file("keys.properties")
val props = Properties()
if (keystoreProperties.exists()) {
    props.load(FileInputStream(keystoreProperties))
} else {
    props["github_token"] = "none"
}

android {
    val stringType = "String"
    compileSdkVersion(30)
    buildToolsVersion("30.0.2")

    defaultConfig {
        applicationId = "com.example.graphspresso"
        minSdkVersion(21)
        targetSdkVersion(30)
        versionCode = 1
        versionName = "1.0"
        multiDexEnabled = true
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {

        getByName("debug") {
            // you can use this with different values inside buildTypes.
            buildConfigField(stringType, "GITHUB_TOKEN", props["github_token"] as String)
            buildConfigField(stringType, "BASE_URL", props["base_url"] as String)
            buildConfigField(stringType, "TOKEN_HEADER_NAME", props["token_header_name"] as String)
            isDebuggable = true
        }

        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    kotlinOptions {
        // We have to add the explicit cast before accessing the options itself.
        val options = this as org.jetbrains.kotlin.gradle.dsl.KotlinJvmOptions
        options.jvmTarget = "1.8"
    }

    compileOptions {
        targetCompatibility = JavaVersion.VERSION_1_8
        sourceCompatibility = JavaVersion.VERSION_1_8
    }

    buildFeatures {
        dataBinding = true
    }

    packagingOptions {
        exclude("META-INF/LICENSE.md")
        exclude("META-INF/LICENSE-notice.md")
    }
}

dependencies {


    implementation(Kotlin.stdlib.jdk8)
    implementation(AndroidX.multidex)
    implementation(AndroidX.core.ktx)
    implementation(AndroidX.appCompat)
    implementation(Google.android.material)
    implementation(AndroidX.constraintLayout)

    implementation(Square.okHttp3.okHttp)
    implementation(Square.okHttp3.loggingInterceptor)

    implementation("com.apollographql.apollo:apollo-runtime:_")
    implementation("com.apollographql.apollo:apollo-normalized-cache-sqlite:_")
    implementation("com.apollographql.apollo:apollo-coroutines-support:_")
    implementation("com.apollographql.apollo:apollo-android-support:_")

    implementation(AndroidX.lifecycle.runtime)
    implementation(AndroidX.lifecycle.viewModelKtx)
    implementation(AndroidX.lifecycle.liveDataKtx)

    implementation(AndroidX.recyclerView)
    implementation(AndroidX.navigation.fragmentKtx)
    implementation(AndroidX.navigation.uiKtx)
    implementation(KotlinX.coroutines.core)
    implementation(KotlinX.coroutines.android)

    implementation(COIL)

    // Koin
    implementation("org.koin:koin-android:_")
    implementation("org.koin:koin-androidx-viewmodel:_")
    implementation("org.koin:koin-androidx-scope:_")
    implementation("org.koin:koin-androidx-ext:_")

    //Loading
    implementation("com.wang.avi:library:_")

    // Testing
    implementation(AndroidX.test.core)
    implementation(AndroidX.test.coreKtx)
    implementation(AndroidX.test.ext.junit)
    implementation(AndroidX.test.ext.junitKtx)
    implementation(AndroidX.test.runner)
    implementation(AndroidX.test.rules)
    androidTestImplementation("com.kaspersky.android-components:kaspresso:_")
    androidTestImplementation ("io.kotlintest:kotlintest-runner-junit5:_")


}

apollo {
    // instruct the compiler to generate Kotlin models
    generateKotlinModels.set(true)
}