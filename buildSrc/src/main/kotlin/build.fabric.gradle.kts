import buildlogic.Utils
import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar
import net.fabricmc.loom.task.RemapJarTask
import net.fabricmc.loom.task.RemapSourcesJarTask

plugins {
    id("build.common")
    id("build.shadow")
    id("fabric-loom")
    id("com.gradleup.shadow")
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

val finalShadow = tasks.register<ShadowJar>("finalShadow") {

    val remapJar = tasks.named<RemapJarTask>("remapJar").get()
    dependsOn(remapJar)
    from(zipTree(remapJar.archiveFile))

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
