plugins {
    id("com.android.application") version "8.0.1" apply false
    id("org.jetbrains.kotlin.android") version "1.8.10" apply false
    id("com.google.devtools.ksp") version "1.8.10-1.0.9" apply false
    id("com.google.protobuf") version "0.9.1" apply false
    kotlin("plugin.serialization") version "1.8.10"
    id("io.gitlab.arturbosch.detekt") version "1.22.0"
}