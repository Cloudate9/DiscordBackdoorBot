plugins {
    id("com.github.johnrengelman.shadow") version "7.1.2"
    kotlin("jvm") version "1.6.10"
    kotlin("plugin.serialization") version "1.6.10"
}

group = "io.github.awesomemoder316.discordbackdoorbot"
version = "1.0.0"

repositories {
    mavenCentral()
}

dependencies {
    implementation("com.diogonunes:JColor:5.2.0")
    implementation("io.insert-koin:koin-core:3.1.4")
    implementation("org.javacord:javacord:3.3.2")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.3.2")
}


tasks {
    compileKotlin {
        kotlinOptions {
            jvmTarget = "11"
        }
    }

    shadowJar {
        archiveFileName.set(rootProject.name + "-" + rootProject.version + ".jar")
        relocate("com.diogonunes", "io.github.discordbackdoorbot.dependencies.diogonunes")
        relocate("org.jetbrains", "io.github.awesomemoder316.discordbackdoorbot.dependencies.jetbrains")
        //Relocating javacord causes issues. If anyone knows how to solve this, please open a pr. Thank you :D
        relocate("org.koin", "io.github.awesomemoder316.discordbackdoorbot.dependencies.koin")
    }

    jar {
        manifest {
            attributes["Main-Class"] = "io.github.awesomemoder316.discordbackdoorbot.DiscordBackdoorBotKt"
        }
    }
}