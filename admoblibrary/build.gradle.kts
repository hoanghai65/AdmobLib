plugins {
    id("com.android.library")
//    id("com.android.application")
    id("maven-publish")
    id("org.jetbrains.kotlin.android")

}

android {
    namespace = "com.haihd1.abmoblibrary"
    compileSdk = 34

    defaultConfig {
        minSdk = 24
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
        multiDexEnabled = true
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

java {
    sourceCompatibility = JavaVersion.VERSION_17            // << --- ADD This
    targetCompatibility = JavaVersion.VERSION_17
}

dependencies {
    implementation("androidx.core:core-ktx:1.8.0")

    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.8.0")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    //Firebase
    implementation(platform("com.google.firebase:firebase-bom:32.7.2"))
    implementation("com.google.firebase:firebase-core:21.1.1")
    implementation("com.google.firebase:firebase-analytics")
    implementation("com.google.firebase:firebase-config:21.6.3")
    implementation("com.google.firebase:firebase-crashlytics")

    implementation("com.google.android.gms:play-services-ads:23.0.0")

    implementation("com.google.android.ump:user-messaging-platform:2.2.0")
    implementation("com.facebook.shimmer:shimmer:0.5.0")
    implementation("com.github.ybq:Android-SpinKit:1.4.0")
    implementation("androidx.lifecycle:lifecycle-extensions:2.2.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.7.0")
    implementation ("androidx.lifecycle:lifecycle-common:2.7.0")
    implementation("androidx.multidex:multidex:2.0.1")

    // appsflyer
    implementation("com.appsflyer:af-android-sdk:6.12.1")
    implementation("com.appsflyer:adrevenue:6.4.3")
    implementation("com.android.installreferrer:installreferrer:2.2")
    implementation("com.miui.referrer:homereferrer:1.0.0.6")


    //adjust
    implementation ("com.adjust.sdk:adjust-android:4.38.0")
    implementation("com.google.android.gms:play-services-ads-identifier:18.0.1")
    implementation("com.google.android.gms:play-services-appset:16.0.2")
    implementation ("com.android.installreferrer:installreferrer:2.2")
    implementation ("com.google.android.gms:play-services-basement:18.3.0")

    //facebook sdk
    implementation ("com.facebook.android:facebook-android-sdk:16.1.2")
}

publishing {
    publications {
        register<MavenPublication>("release") {
            afterEvaluate {
                from(components["release"])
            }
        }
    }
}