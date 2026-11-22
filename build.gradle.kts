
tasks.register("clean") {
    doFirst {
        project.delete("$rootDir/target")
    }
}

if (version != "unspecified") logger.lifecycle("building tuffvote $version")
