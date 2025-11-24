plugins {
    id("build.common")
    id("application")
}

tasks.withType<JavaExec>() {
    workingDir = file("run/")
    run {
        mkdir("run/")
    }
}