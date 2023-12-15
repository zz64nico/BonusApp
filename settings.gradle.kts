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
        maven { url =uri("https://jitpack.io") }
        maven {
            url =uri("https://maven.aliyun.com/repository/jcenter")
        }
        maven {
            url =uri("https://maven.aliyun.com/repository/central")
        }
        maven {
            url =uri("https://maven.aliyun.com/repository/public/")
        }
        maven {
            url =uri("https://maven.aliyun.com/repository/google")
        }

    }
}

rootProject.name = "BonusApp"
include(":app")
