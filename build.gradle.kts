import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

val diskordVersion: String by project

plugins {
    kotlin("jvm") version "1.5.31"
    `maven-publish`
}

group = "com.github.myraBot"
val id = "Bunyeokga"
version = "1.7"

repositories {
    mavenCentral()
    maven(url = "https://systems.myra.bot/releases/") {
        credentials {
            username = System.getenv("REPO_NAME")
            password = System.getenv("REPO_SECRET")
        }
    }
}

dependencies {
    compileOnly(group = "com.github.myraBot", name = "Diskord", version = diskordVersion) // Discord Wrapper
}

publishing {
    repositories {
        publications {
            create<MavenPublication>("repo") {
                group = project.group as String
                version = project.version as String
                artifactId = id
                from(components["java"])
            }
        }
        maven {
            url = uri( "https://systems.myra.bot/releases/")
            name = "repo"
            credentials {
                username = System.getenv("REPO_NAME")
                password = System.getenv("REPO_SECRET")
            }
            authentication { create<BasicAuthentication>("basic") }
        }
    }
}

tasks.withType<KotlinCompile>() {
    kotlinOptions.jvmTarget = "1.8"
}