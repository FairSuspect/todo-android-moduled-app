plugins {
    id("android-domain-convention")
    alias(libs.plugins.compose.compiler)
}

android {
    namespace = "io.fairboi.mytodoapp.domain"
}
