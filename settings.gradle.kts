pluginManagement {
    repositories {
        gradlePluginPortal()
        maven {
            url = uri("https://maven.myket.ir")
        }
        google()
        mavenCentral()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        mavenCentral()
        google()
        maven {
            url = uri("https://maven.myket.ir")
        }
    }
}

rootProject.name = "Backgroundable"
include(":app")
