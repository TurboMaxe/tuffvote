plugins {
    tuffvote.common
}

repositories {
    mavenCentral()
    maven{
        name = "papermc-repo"
        url = uri("https://repo.papermc.io/repository/maven-public/")
    }
}

dependencies {
    compileOnly(libs.common.configlib)
    compileOnly(libs.velocity)

    compileOnly(libs.votifier.api)
    compileOnly(libs.votifier.paper)

    annotationProcessor(libs.velocity)
}