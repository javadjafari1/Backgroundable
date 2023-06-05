pluginManagement {
    repositories {
        if (File("properties/nexus.properties").exists()) {
            val localProperties = java.util.Properties().apply {
                load(java.io.FileInputStream("properties/nexus.properties"))
            }
            maven {
                url = uri(localProperties.getProperty("url"))
                credentials {
                    username = localProperties.getProperty("username")
                    password = localProperties.getProperty("password")
                }
            }
        }
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        if (File("properties/nexus.properties").exists()) {
            val localProperties = java.util.Properties().apply {
                load(java.io.FileInputStream("properties/nexus.properties"))
            }
            maven {
                url = uri(localProperties.getProperty("url"))
                credentials {
                    username = localProperties.getProperty("username")
                    password = localProperties.getProperty("password")
                }
            }
        }
        google()
        gradlePluginPortal()
    }
}

rootProject.name = "Backgroundable"
include(":app")
