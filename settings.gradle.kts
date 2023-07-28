pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
rootProject.name = "Dynamic Delivery"
include(":app")
include(":dynamicfeature1")
include(":installtimefeaturemodule")
include(":conditionalfeaturemodule")
include(":dynamicfeature4")
