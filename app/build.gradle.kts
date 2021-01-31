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
    implementation(Libs.kotlin_stdlib)
    implementation(Libs.multidex)

    //Android
    implementation(Libs.appcompat)
    implementation(Libs.androidx_core_core_ktx)
    implementation(Libs.material)
    implementation(Libs.constraintlayout)
    implementation(Libs.recyclerview)

    //Navigation
    implementation(Libs.navigation_fragment_ktx)
    implementation(Libs.navigation_ui_ktx)

    // Networking
    implementation(Libs.apollo_runtime)
    implementation(Libs.apollo_normalized_cache_sqlite)
    implementation(Libs.apollo_coroutines_support)
    implementation(Libs.apollo_android_support)
    implementation(Libs.logging_interceptor)
    implementation(Libs.okhttp)

    //Loader, Image loading
    implementation(Libs.avi_loader)
    implementation(Libs.coil)

    // Coroutines, ViewModel, LiveData
    implementation(Libs.kotlinx_coroutines_core)
    implementation(Libs.kotlinx_coroutines_android)
    implementation(Libs.lifecycle_viewmodel_ktx)
    implementation(Libs.lifecycle_runtime_ktx)
    implementation(Libs.lifecycle_viewmodel_ktx)
    implementation(Libs.lifecycle_livedata_ktx)

    // DI - Koin
    implementation(Libs.koin_android)
    implementation(Libs.koin_androidx_viewmodel)
    implementation(Libs.koin_androidx_scope)
    implementation(Libs.koin_androidx_ext)


    // Testing
    implementation(Libs.androidx_test_core)
    androidTestImplementation(Libs.androidx_test_core_ktx)
    androidTestImplementation(Libs.junit)
    androidTestImplementation(Libs.junit_ktx)
    androidTestImplementation(Libs.androidx_test_runner)
    androidTestImplementation(Libs.androidx_test_rules)
    androidTestImplementation(Libs.kaspresso)
    androidTestImplementation(Libs.kotest)


}

apollo {
    // instruct the compiler to generate Kotlin models
    generateKotlinModels.set(true)
}