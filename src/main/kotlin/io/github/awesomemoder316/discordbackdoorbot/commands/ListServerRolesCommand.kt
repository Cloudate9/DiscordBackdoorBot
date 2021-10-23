package io.github.awesomemoder316.discordbackdoorbot.commands

import com.diogonunes.jcolor.Ansi.colorize
import com.diogonunes.jcolor.Attribute
import io.github.awesomemoder316.discordbackdoorbot.DiscordBackdoorBot

class ListServerRolesCommand(private val discordBackdoorBot: DiscordBackdoorBot): IBackdoorBotCommands {

    override fun onCommand() {
        println(colorize("Operation: List all roles in the server.", Attribute.CYAN_TEXT()))

        //Purposeful lack of colorization for list command, as looking at a chunk of text in one colour is tiring.
        println("All roles in server: ")

        val roles = discordBackdoorBot.server.roles

        for ((index, role) in roles.withIndex()) {
            println("${index + 1}. $role") //index + 1 as we want to show 1 indexed, not 0 indexed.
        }
    }
}