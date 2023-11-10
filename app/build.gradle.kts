import org.jetbrains.kotlin.konan.properties.Properties

plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.kotlin)
    alias(libs.plugins.kapt)
    alias(libs.plugins.kotlinSerialization)
    alias(libs.plugins.protobuf)
    alias(libs.plugins.detekt)
    alias(libs.plugins.ksp)
}

detekt {
    autoCorrect = true
    buildUponDefaultConfig = true
    config.from(files("$rootDir/detekt.yml"))
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
        versionCode = 5
        versionName = "1.2.2"

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
        freeCompilerArgs += "-opt-in=kotlinx.coroutines.ExperimentalCoroutinesApi"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.composeCompiler.get()
    }
    testOptions {
        unitTests.isReturnDefaultValues = true
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
    detektPlugins(libs.detektFormatting)

    /*Compose*/
    implementation(libs.bundles.compose)
    implementation(libs.activityCompose)
    implementation(libs.navigationCompose)
    implementation(libs.composeUi)
    implementation(libs.composeMaterial3)
    implementation(libs.lifecycleRuntimeCompose)
    implementation(libs.composeMaterial) {
        because("just for the swipe refresh")
    }
    implementation(libs.coilCompose)

    /*coroutine*/
    implementation(libs.coroutine)

    /*Retrofit*/
    implementation(libs.retrofit)
    implementation(libs.kotlinxSerialization)
    implementation(libs.retrofitKotlinxSerializationConverter)

    /*dagger*/
    implementation(libs.dagger)
    kapt(libs.daggerCompiler)

    /*room*/
    implementation(libs.bundles.room)
    ksp(libs.roomCompiler)

    /*chucker*/
    debugImplementation(libs.chucker)
    releaseImplementation(libs.chuckerNoOp)

    /*datastore*/
    implementation(libs.bundles.datastore)
    implementation(libs.datastorePreferences)

    implementation(libs.coreKtx)
    implementation(libs.lifecycleRuntimeKtx)
    implementation(libs.bundles.okhttp)
    implementation(libs.bundles.paging)
    implementation(libs.zoomableImageCoil)
    implementation(libs.accompanistNavigationMaterial)
    implementation(libs.splashscreen)

    testImplementation(libs.junit)
    testImplementation(libs.kotestAssertion)
    testImplementation(libs.mockK)
    testImplementation(libs.turbine)
    testImplementation(libs.coroutineTest)
    androidTestImplementation(libs.androidxJunit)
    androidTestImplementation(libs.espressoCore)
    androidTestImplementation(libs.composeUiJunit4)
    debugImplementation(libs.composeUiTooling)
    debugImplementation(libs.composeUiManifest)
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