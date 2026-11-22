plugins {
    java
    id("com.gradleup.shadow")
}

repositories {
    mavenCentral()
    gradlePluginPortal()
    maven("https://jitpack.io")
    maven(("https://repo.papermc.io/repository/maven-public/"))
    maven("https://libraries.minecraft.net")
}

dependencies {
    compileOnly("org.projectlombok:lombok:1.18.38")
    annotationProcessor("org.projectlombok:lombok:1.18.38")
    compileOnly("com.velocitypowered:velocity-api:3.4.0")
    compileOnly("net.md-5:bungeecord-api:1.21-R0.2")
    compileOnly("org.jetbrains:annotations:26.0.2")

    implementation("de.exlll:configlib-yaml:4.8.1")
    annotationProcessor("org.jetbrains:annotations:26.0.2")
}

tasks {
    withType<com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar> {
        archiveFileName.set("${rootProject.name}-${project.name}-$version.jar")
    }

    named("build") {
        dependsOn("shadowJar")
    }
}