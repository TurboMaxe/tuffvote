import org.gradle.internal.declarativedsl.intrinsics.listOf

pluginManagement {
    plugins {
        kotlin("jvm") version "2.2.0"
    }
}
rootProject.name = "tuffvote"
include("common",
        "velocity",
        "bungee"
)
