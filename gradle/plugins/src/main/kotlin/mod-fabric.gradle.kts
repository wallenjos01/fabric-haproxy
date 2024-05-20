import build.plugin.Common
import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar
import net.fabricmc.loom.task.RemapJarTask
import net.fabricmc.loom.task.RemapSourcesJarTask

plugins {
    id("java")
    id("java-library")
    id("maven-publish")
    id("com.github.johnrengelman.shadow")
    id("fabric-loom")
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

val archiveName: String = Common.getArchiveName(project, rootProject)

val finalShadow = tasks.register<ShadowJar>("finalShadow") {

    val remapJar = tasks["remapJar"]

    dependsOn(remapJar)
    from(remapJar)

    archiveClassifier.set("")
    archiveBaseName.set(archiveName)
    configurations = listOf(project.configurations["shadow"])
}


tasks.named("build") {
    dependsOn(finalShadow)
}
tasks.named<Jar>("jar") {
    archiveBaseName.set(archiveName)
    archiveClassifier.set("partial-dev")
}
tasks.named<RemapJarTask>("remapJar") {
    archiveBaseName.set(archiveName)
    archiveClassifier.set("partial")
    inputFile.set(tasks.named<Jar>("jar").get().archiveFile)
}
tasks.named<RemapSourcesJarTask>("remapSourcesJar") {
    archiveBaseName.set(archiveName)
    archiveClassifier.set("sources")
}
tasks.named<ShadowJar>("shadowJar") {
    enabled = false
}

