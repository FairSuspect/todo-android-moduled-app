plugins {
    alias(libs.plugins.compose.compiler)

    id("android-app-convention")
}
android {
    namespace = "io.fairboi.mytodoapp"
    defaultConfig {
        applicationId = "io.fairboi.mytodoapp"
        versionCode = 1
        versionName = "1.0"
    }
}

dependencies {

    api(project(":feature:settings"))
    api(project(":feature:list"))


    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)

    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}