plugins {
    id("android-feature-convention")
    alias(libs.plugins.compose.compiler)
}

android {
    namespace = "io.fairboi.list"

}
dependencies {
    api(project(":domain"))
    api(project(":core:data"))
    api(project(":core:theme"))
    api(project(":core:ui"))

    implementation(libs.androidx.compose.material.icons.extended)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}