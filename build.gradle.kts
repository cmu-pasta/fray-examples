/*
 * This file was generated by the Gradle 'init' task.
 *
 * This is a general purpose Gradle build.
 * Learn more about Gradle by exploring our Samples at https://docs.gradle.org/8.9/samples
 */
plugins {
    id("java")
    id("org.pastalab.fray.gradle") version "0.1"
}


repositories {
    mavenCentral()
    maven {
        url = uri("https://maven.pkg.github.com/cmu-pasta/fray")
        credentials {
            username = extra["gpr.user"] as String? ?: System.getenv("USERNAME")
            password = extra["gpr.key"] as String? ?: System.getenv("TOKEN")
        }
    }
}

fray {
    version = "0.1"
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.2"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.test {
    dependsOn("frayTest")
}