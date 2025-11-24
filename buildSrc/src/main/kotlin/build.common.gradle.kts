import buildlogic.Utils;

plugins {
    id("java")
}

repositories {
    mavenCentral()
    maven("https://maven.wallentines.org/releases")

    if(rootProject.version.toString().endsWith("-SNAPSHOT")) {
        maven("https://maven.wallentines.org/snapshots")
    }
}

dependencies {

    testImplementation("org.junit.jupiter:junit-jupiter:5.10.2")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
    withSourcesJar()
}

tasks.withType<Jar> {
    archiveBaseName.set(Utils.getArchiveName(project, rootProject))
}

tasks.named<Test>("test") {
    useJUnitPlatform()
    workingDir("run/test")
    run {
        mkdir("run/test")
    }
}
