plugins {
    id("android-core-convention")
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "io.fairboi.ui"
}

dependencies {
    api(project(":domain"))
    api(project(":core:theme"))
}