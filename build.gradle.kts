import build.plugin.Common

plugins {
    id("mod-build")
    id("mod-fabric")
    id("mod-publish")
}

Common.setupResources(project, rootProject, "fabric.mod.json")

repositories {
    maven(url = "https://s01.oss.sonatype.org/content/repositories/snapshots/") {
        name = "sonatype-oss-snapshots1"
        mavenContent { snapshotsOnly() }
    }
    maven("https://oss.sonatype.org/content/repositories/snapshots")
}

dependencies {
    minecraft("com.mojang:minecraft:1.21.6-pre1")
    mappings(loom.officialMojangMappings())

    modImplementation("net.fabricmc:fabric-loader:0.16.14")
    modImplementation(include("io.netty:netty-codec-haproxy:4.1.115.Final")!!)
}
