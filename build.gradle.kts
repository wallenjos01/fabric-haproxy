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
    minecraft("com.mojang:minecraft:1.21.10")
    mappings(loom.officialMojangMappings())

    modImplementation("net.fabricmc:fabric-loader:0.17.3")
    modImplementation(include("io.netty:netty-codec-haproxy:4.1.115.Final")!!)
}
