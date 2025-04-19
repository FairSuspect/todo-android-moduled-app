import gradle.kotlin.dsl.accessors._8e8a6dd48b2094ffcd3758431423791d.annotationProcessor
import gradle.kotlin.dsl.accessors._8e8a6dd48b2094ffcd3758431423791d.debugImplementation
import gradle.kotlin.dsl.accessors._8e8a6dd48b2094ffcd3758431423791d.implementation
import gradle.kotlin.dsl.accessors._8e8a6dd48b2094ffcd3758431423791d.ksp
import gradle.kotlin.dsl.accessors._8e8a6dd48b2094ffcd3758431423791d.testImplementation

plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("com.google.devtools.ksp")
}

android {
    baseAndroidConfig()
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = AndroidConst.COMPOSE_COMPILER_VERSION
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}

dependencies {


    // Dagger 2
    implementation(libs.dagger)
    ksp(libs.dagger.compiler)

    // Room
    implementation(libs.androidx.room.runtime)
    annotationProcessor(libs.androidx.room.compiler)
    ksp(libs.androidx.room.compiler)
    implementation(libs.androidx.room.ktx)

    // Work manager
    implementation(libs.androidx.work.runtime.ktx)

    // DataStore
    implementation(libs.androidx.datastore.preferences)

    // Ktor
    implementation(libs.ktor.serialization.kotlinx.json)

    implementation(libs.ktor.client.core)
    implementation(libs.ktor.client.cio)

    implementation(libs.ktor.client.content.negotiation)





    testImplementation(libs.junit)
    testImplementation(libs.junit.jupiter)
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