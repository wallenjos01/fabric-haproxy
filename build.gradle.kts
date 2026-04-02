import buildlogic.Utils

plugins {
    id("build.library")
    id("build.fabric")
    id("build.publish")
}

Utils.setupResources(project, rootProject, "fabric.mod.json")

repositories {
    maven(url = "https://s01.oss.sonatype.org/content/repositories/snapshots/") {
        name = "sonatype-oss-snapshots1"
        mavenContent { snapshotsOnly() }
    }
    maven("https://oss.sonatype.org/content/repositories/snapshots")
}

dependencies {
    minecraft("com.mojang:minecraft:${project.properties["minecraft-version"]}")

    implementation("net.fabricmc:fabric-loader:${project.properties["fabric-loader-version"]}")
    implementation(include("io.netty:netty-codec-haproxy:4.2.7.Final")!!)
}
