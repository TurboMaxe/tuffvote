import org.gradle.internal.impldep.org.apache.http.client.methods.RequestBuilder.options
import org.gradle.model.internal.core.ModelNodes.withType

plugins {
    java
    id("com.gradleup.shadow")
}

version = "1.0.0"

repositories {
    mavenCentral()
    gradlePluginPortal()
    maven("https://jitpack.io")
}

dependencies {
    compileOnly("org.projectlombok:lombok:1.18.38")
    annotationProcessor("org.projectlombok:lombok:1.18.38")

    implementation(project(":common"))

    compileOnly("org.jetbrains:annotations:26.0.2")
    annotationProcessor("org.jetbrains:annotations:26.0.2")
}

tasks {
    withType<com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar> {
        destinationDirectory.set(file("$rootDir/target"))
        archiveFileName.set("${rootProject.name}-${project.name}-$version.jar")
    }

    withType<JavaCompile> {
        options.compilerArgs.add("-parameters")
        options.isFork = true
        options.encoding = "UTF-8"
        options.release = 21
    }

    named("build") {
        dependsOn("shadowJar")
    }
}