plugins {
    id("android-core-convention")
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "io.fairboi.mytodoapp.data"
}

dependencies {
    api(project(":domain"))
    api(project(":core:db"))
}