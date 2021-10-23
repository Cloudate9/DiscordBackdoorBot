package io.github.awesomemoder316.discordbackdoorbot.commands

import com.diogonunes.jcolor.Ansi.colorize
import com.diogonunes.jcolor.Attribute
import kotlin.system.exitProcess

class StopCommand: IBackdoorBotCommands {

    override fun onCommand() {
        println(colorize("Stop command received. DiscordBackdoorBot is now shutting down...", Attribute.GREEN_TEXT()))
        exitProcess(0)
    }
}
