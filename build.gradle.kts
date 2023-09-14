plugins {
    alias(libs.plugins.androidApplication) apply false
    alias(libs.plugins.kotlin) apply false
    alias(libs.plugins.ksp) apply false
    alias(libs.plugins.protobuf) apply false
    alias(libs.plugins.kotlinSerialization)
    alias(libs.plugins.detekt)
}