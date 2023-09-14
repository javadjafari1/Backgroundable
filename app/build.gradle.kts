import org.jetbrains.kotlin.konan.properties.Properties

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("org.jetbrains.kotlin.plugin.serialization")
    id("io.gitlab.arturbosch.detekt")
    id("com.google.devtools.ksp")
    id("com.google.protobuf")
}

detekt {
    autoCorrect = true
    buildUponDefaultConfig = true
    config = files("$rootDir/detekt.yml")
}

android {
    namespace = "ir.thatsmejavad.backgroundable"
    compileSdk = 34
    val properties = Properties()
    val propertiesExist = rootProject.file("properties/authorization.properties").exists()
    if (propertiesExist) {
        properties.load(
            project.rootProject.file("properties/authorization.properties").inputStream()
        )
    }

    defaultConfig {
        applicationId = "ir.thatsmejavad.backgroundable"
        minSdk = 24
        targetSdk = 34
        versionCode = 3
        versionName = "1.1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
        val authorization = if (propertiesExist) {
            properties.getProperty("authorization")
        } else {
            "AUTHORIZATION"
        }

        buildConfigField(
            type = "String",
            value = "\"$authorization\"",
            name = "AUTHORIZATION",
        )
    }

    val securityProperties = Properties()
    val securityPropertiesExist = rootProject.file("properties/security.properties").exists()
    val keyStoreFileExist = rootProject.file("Backgroundable.jks").exists()
    if (securityPropertiesExist && keyStoreFileExist) {
        securityProperties.load(
            project.rootProject.file("properties/security.properties").inputStream()
        )

        signingConfigs {
            create("release") {
                storeFile = rootProject.file("Backgroundable.jks")
                storePassword = securityProperties.getProperty("storePassword")
                keyAlias = securityProperties.getProperty("alias")
                keyPassword = securityProperties.getProperty("keyPassword")
            }
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            isShrinkResources = true
            isDebuggable = false
            if (securityPropertiesExist && keyStoreFileExist) {
                signingConfig = signingConfigs["release"]
            }
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
        debug {
            isMinifyEnabled = false
            isShrinkResources = false
            isDebuggable = true
            applicationIdSuffix = ".debug"
            versionNameSuffix = ".debug"

        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
        freeCompilerArgs += "-opt-in=androidx.compose.foundation.ExperimentalFoundationApi"
        freeCompilerArgs += "-opt-in=androidx.compose.material3.ExperimentalMaterial3Api"
        freeCompilerArgs += "-opt-in=androidx.paging.ExperimentalPagingApi"
        freeCompilerArgs += "-opt-in=com.google.accompanist.navigation.material.ExperimentalMaterialNavigationApi"
        freeCompilerArgs += "-opt-in=kotlinx.serialization.ExperimentalSerializationApi"
        freeCompilerArgs += "-opt-in=kotlinx.coroutines.FlowPreview"
        freeCompilerArgs += "-opt-in=androidx.compose.material.ExperimentalMaterialApi"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

ksp {
    arg(RoomSchemaArgProvider(File(projectDir, "schemas")))
}

protobuf {
    protoc {
        artifact = "com.google.protobuf:protoc:21.0-rc-1"
    }
    generateProtoTasks {
        all().forEach { task ->
            task.builtins {
                create("java") {
                    option("lite")
                }
            }
        }
    }
}

dependencies {

    detektPlugins("io.gitlab.arturbosch.detekt:detekt-formatting:1.23.1")

    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.2")

    implementation("androidx.activity:activity-compose:1.7.2")
    implementation("androidx.navigation:navigation-compose:2.7.2")

    val composeUi = "1.5.1"
    implementation("androidx.compose.ui:ui:$composeUi")
    implementation("androidx.compose.ui:ui-graphics:$composeUi")
    implementation("androidx.compose.ui:ui-tooling-preview:$composeUi")
    implementation("androidx.compose.material3:material3:1.2.0-alpha04")

    implementation("androidx.lifecycle:lifecycle-runtime-compose:2.6.2")

    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.5.0")
    implementation("com.jakewharton.retrofit:retrofit2-kotlinx-serialization-converter:1.0.0")

    implementation(platform("com.squareup.okhttp3:okhttp-bom:4.10.0"))
    implementation("com.squareup.okhttp3:okhttp")
    implementation("com.squareup.okhttp3:logging-interceptor")

    implementation("androidx.paging:paging-common-ktx:3.2.1")
    implementation("com.google.dagger:dagger:2.47")
    kapt("com.google.dagger:dagger-compiler:2.47")

    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.03.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")

    val paging = "3.2.1"
    implementation("androidx.paging:paging-runtime-ktx:$paging")
    implementation("androidx.paging:paging-common-ktx:$paging")
    implementation("androidx.paging:paging-compose:$paging")
    implementation("io.coil-kt:coil-compose:2.4.0")

    val roomVersion = "2.5.2"
    implementation("androidx.room:room-runtime:$roomVersion")
    implementation("androidx.room:room-paging:$roomVersion")
    implementation("androidx.room:room-ktx:$roomVersion")
    ksp("androidx.room:room-compiler:$roomVersion")

    debugImplementation("com.github.chuckerteam.chucker:library:4.0.0")
    releaseImplementation("com.github.chuckerteam.chucker:library-no-op:3.5.2")

    implementation("me.saket.telephoto:zoomable-image-coil:0.4.0")
    implementation("androidx.compose.material:material:1.5.1") {
        because("just for the swipe refresh")
    }

    implementation("com.google.accompanist:accompanist-navigation-material:0.32.0")

    implementation("androidx.datastore:datastore-preferences:1.0.0")

    implementation("androidx.constraintlayout:constraintlayout-compose:1.0.1")

    implementation("androidx.datastore:datastore:1.0.0")
    implementation("com.google.protobuf:protobuf-javalite:3.23.0")

    implementation("androidx.core:core-splashscreen:1.0.1")
}

class RoomSchemaArgProvider(
    @get:InputDirectory
    @get:PathSensitive(PathSensitivity.RELATIVE)
    val schemaDir: File
) : CommandLineArgumentProvider {

    override fun asArguments(): Iterable<String> {
        return listOf("room.schemaLocation=${schemaDir.path}")
    }
}