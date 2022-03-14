package io.github.cloudate9.discordbackdoorbot.commands

import com.diogonunes.jcolor.Ansi.colorize
import com.diogonunes.jcolor.Attribute
import io.github.cloudate9.discordbackdoorbot.DiscordBackdoorBot
import io.github.cloudate9.discordbackdoorbot.json.ConfigJson
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlin.io.path.writeText

class SetupCommand(private val discordBackdoorBot: DiscordBackdoorBot) : IBackdoorBotCommands {

    override fun onCommand() {
        println(colorize("Operation: Setup assistant.", Attribute.CYAN_TEXT()))
        println(colorize("Setup assistant running...", Attribute.GREEN_TEXT()))
        println(
            "If you are at anytime unsure of how to get any of the information, " +
                    "please refer to the README at https://github.com/awesomemoder316/DiscordBackdoorBot"
        )

        Thread.sleep(500)

        println(colorize("Enter your Discord bot token.", Attribute.CYAN_TEXT()))
        print("> ")

        val botToken = readLine() ?: ""

        println(colorize("Enter your Discord server id.", Attribute.CYAN_TEXT()))
        print("> ")

        val serverId = readLine()?.toLongOrNull() ?: 0

        val newConfigValues = ConfigJson(botToken, serverId)
        discordBackdoorBot.configPath.writeText(Json.encodeToString(newConfigValues))

        println(
            colorize(
                "If you don't know what commands are available, use \"help\" to view all commands.",
                Attribute.GREEN_TEXT()
            )
        )

    }
}
