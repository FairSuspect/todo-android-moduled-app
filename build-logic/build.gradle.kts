plugins {
    `kotlin-dsl`
    id("java-gradle-plugin")

}


dependencies {
    implementation(libs.agp)
    implementation(libs.kotlin.gradle.plugin)
    implementation(libs.kotlin.ksp.gradle)

    implementation(libs.kotlin.coroutines.core)




    implementation(files(libs.javaClass.superclass.protectionDomain.codeSource.location))
}