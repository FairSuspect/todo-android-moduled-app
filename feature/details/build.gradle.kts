plugins {
    id("android-feature-convention")
    alias(libs.plugins.compose.compiler)
}

android {
    namespace = "io.fairboi.details"
    defaultConfig {

    }
}

dependencies {
    api(project(":domain"))
    api(project(":core:data"))
    api(project(":core:ui"))
    api(project(":core:theme"))

    implementation(libs.androidx.compose.material.icons.extended)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}