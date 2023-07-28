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
include(":ondemandmodule")
include(":installtimefeaturemodule")
include(":conditionalfeaturemodule")
include(":ondemandmodule2")
