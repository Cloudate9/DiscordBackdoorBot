package io.github.cloudate9.discordbackdoorbot.commands

import com.diogonunes.jcolor.Ansi.colorize
import com.diogonunes.jcolor.Attribute
import io.github.cloudate9.discordbackdoorbot.DiscordBackdoorBot
import org.javacord.api.entity.permission.Permissions

class InviteCommand(private val discordBackdoorBot: DiscordBackdoorBot) : IBackdoorBotCommands {

    override fun onCommand() {
        println(colorize("Operation: Display invite link.", Attribute.CYAN_TEXT()))
        println(
            colorize(
                "Invite link:" +
                        discordBackdoorBot.discordBotImplementation.createBotInvite(Permissions.fromBitmask(8)),
                Attribute.GREEN_TEXT()
            )
        )
    }
}