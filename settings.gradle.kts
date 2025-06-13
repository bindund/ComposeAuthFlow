pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
}
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
rootProject.name = "loginscreens"
include(":app")
include(":auth:data")
include(":auth:presentation")
include(":auth:domain")
include(":core:presentation:designsystem")
include(":core:presentation:ui")
include(":core:data")
include(":core:domain")
include(":core:database")
include(":run:data")
include(":run:presentation")
include(":run:location")
include(":run:domain")
include(":run:network")
include(":core:notifications")
