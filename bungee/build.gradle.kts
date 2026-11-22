plugins {
    tuffvote.common
}

repositories {
    mavenCentral()
    maven("https://libraries.minecraft.net")
}

dependencies {
    compileOnly(libs.bungeecord)
    compileOnly(libs.common.configlib)
    compileOnly(libs.votifier.bungee)
    compileOnly(libs.votifier.api)
}