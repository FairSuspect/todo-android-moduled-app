import gradle.kotlin.dsl.accessors._8e8a6dd48b2094ffcd3758431423791d.androidTestImplementation
import gradle.kotlin.dsl.accessors._8e8a6dd48b2094ffcd3758431423791d.debugImplementation
import gradle.kotlin.dsl.accessors._8e8a6dd48b2094ffcd3758431423791d.implementation
import gradle.kotlin.dsl.accessors._8e8a6dd48b2094ffcd3758431423791d.testImplementation
import java.util.Properties

plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("com.google.devtools.ksp")
}

android {
    baseAndroidConfig()
    buildFeatures {
        compose = true
        viewBinding = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = AndroidConst.COMPOSE_COMPILER_VERSION
    }
    defaultConfig {

    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}

dependencies {


    // Dagger 2
    implementation(libs.dagger)
    ksp(libs.dagger.compiler)


    implementation(libs.material3)

    // Compose navigation
    implementation(libs.androidx.navigation.compose)





    // Testing
    testImplementation(libs.junit)

    testImplementation(libs.androidx.junit)
    testImplementation(libs.androidx.ui.test.junit4)


    implementation(libs.kotlinx.serialization.json)

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.tooling.preview)
}