plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("plugin.serialization")
    id("com.github.ben-manes.versions")
}

android {
    namespace = "eu.kanade.tachiyomi.source"

    defaultConfig {
        consumerProguardFile("consumer-proguard.pro")
    }
}

dependencies {
    implementation(project(":core"))

    api(kotlinx.serialization.json)
    api(libs.injekt.core)
    api(libs.rxjava)
    api(libs.preferencektx)
    api(libs.jsoup)

    // SY -->
    implementation(project(":i18n"))
    implementation(kotlinx.reflect)
    // SY <--
}