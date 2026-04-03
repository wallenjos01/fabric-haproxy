import buildlogic.Utils


plugins {
    id("build.common")
    id("net.fabricmc.fabric-loom")
}

loom {
    runs {
        getByName("client") {
            runDir = "run/client"
            ideConfigGenerated(false)
            client()
        }
        getByName("server") {
            runDir = "run/server"
            ideConfigGenerated(false)
            server()
        }
    }
    mixin {
        defaultRefmapName = "${rootProject.name}.refmap.json"
    }
}

val archiveName = Utils.getArchiveName(project, rootProject)

tasks.named<Jar>("jar") {
    archiveBaseName.set(archiveName)
}
