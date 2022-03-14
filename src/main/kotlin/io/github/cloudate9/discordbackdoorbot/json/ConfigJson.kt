package io.github.cloudate9.discordbackdoorbot.json

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

val format = Json { prettyPrint = true }

@Serializable
data class ConfigJson(
    val discordBotToken: String,
    val serverId: Long
)
