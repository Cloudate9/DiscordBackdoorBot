plugins {
    id("com.github.johnrengelman.shadow") version "7.1.2"
    kotlin("jvm") version "1.6.21"
    kotlin("plugin.serialization") version "1.6.21"
}

group = "io.github.cloudate9.discordbackdoorbot"
version = "1.0.0"

repositories {
    mavenCentral()
}

dependencies {
    implementation("com.diogonunes:JColor:5.5.0")
    implementation("io.insert-koin:koin-core:3.2.0")
    implementation("org.javacord:javacord:3.4.0")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.3.3")
}


tasks {
    compileKotlin {
        kotlinOptions {
            jvmTarget = "11"
        }
    }

    shadowJar {
        archiveFileName.set(rootProject.name + "-" + rootProject.version + ".jar")
        relocate("com.diogonunes", "${rootProject.group}.discordbackdoorbot.dependencies.diogonunes")
        relocate("org.jetbrains", "${rootProject.group}.discordbackdoorbot.dependencies.jetbrains")
        //Relocating javacord causes issues. If anyone knows how to solve this, please open a pr. Thank you :D
        relocate("org.koin", "${rootProject.group}.discordbackdoorbot.dependencies.koin")
    }

    jar {
        manifest {
            attributes["Main-Class"] = "${rootProject.group}.discordbackdoorbot.DiscordBackdoorBotKt"
        }
    }
}