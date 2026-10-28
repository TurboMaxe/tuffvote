plugins {
    java
}

group = "net.cirsius"
version = "2"

repositories {
    mavenCentral()
    maven("https://repo.papermc.io/repository/maven-public/")
    maven("https://jitpack.io")
}

dependencies {
    compileOnly("net.md-5:bungeecord-api:1.21-R0.4")
    compileOnly("com.velocitypowered:velocity-api:3.4.0")
    annotationProcessor("com.velocitypowered:velocity-api:3.4.0")

    compileOnly("com.github.nuvotifier.NuVotifier:nuvotifier-api:2.7.3")
    compileOnly("com.github.nuvotifier.NuVotifier:nuvotifier-bungeecord:2.7.3")
    compileOnly("com.github.nuvotifier.NuVotifier:nuvotifier-velocity:2.7.3")

    compileOnly("org.projectlombok:lombok:1.18.34")
    annotationProcessor("org.projectlombok:lombok:1.18.34")
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(17))
    }
}

tasks {

    processResources {
        filesMatching(listOf("bungee.yml", "velocity-plugin.json")) {
            expand("version" to project.version)
        }
    }

    jar {
        archiveBaseName.set("tuffvote")
        archiveClassifier.set("")
    }
}
