plugins {
    id("com.android.application") version "8.1.1" apply false
    id("org.jetbrains.kotlin.android") version "1.9.0" apply false
    id("com.google.devtools.ksp") version "1.9.0-1.0.13" apply false
    id("com.google.protobuf") version "0.9.4" apply false
    kotlin("plugin.serialization") version "1.9.0"
    id("io.gitlab.arturbosch.detekt") version "1.22.0"
}