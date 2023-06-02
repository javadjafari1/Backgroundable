pluginManagement {
    val localProperties = java.util.Properties().apply {
        load(java.io.FileInputStream("nexus.properties"))
    }

    repositories {
        maven {
            url = uri(localProperties.getProperty("url"))
            credentials {
                username = localProperties.getProperty("username")
                password = localProperties.getProperty("password")
            }
        }
    }
}
dependencyResolutionManagement {
    val localProperties = java.util.Properties().apply {
        load(java.io.FileInputStream("nexus.properties"))
    }
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        maven {
            url =
                uri(localProperties.getProperty("url"))
            credentials {
                username = localProperties.getProperty("username")
                password = localProperties.getProperty("password")
            }
        }
    }
}

rootProject.name = "Backgroundable"
include(":app")
