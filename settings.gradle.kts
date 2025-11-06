pluginManagement {
    repositories {
        // Quitamos las restricciones 'content' para permitir que encuentre todos los plugins
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
    }
}

rootProject.name = "Zoonari"
include(":app")

 