package io.github.cloudate9.discordbackdoorbot.commands.sorter

import com.diogonunes.jcolor.Ansi.colorize
import com.diogonunes.jcolor.Attribute
import io.github.cloudate9.discordbackdoorbot.commands.IBackdoorBotCommands

class CommandSorter: ICommandSorter {
    private val registeredCommands = HashMap<String, IBackdoorBotCommands>() //Command name, implementation.

    override fun callAppropriateCommand(command: String) {
        for ((registeredCommand, commandType) in registeredCommands) {
            if (command.lowercase() == registeredCommand) {
                commandType.onCommand()
                return
            }
        }

        println(colorize("The command \"$command\" was not found! Enter \"help\" for help.", Attribute.RED_TEXT()))
    }

    override fun registerCommand(command: String, commandType: IBackdoorBotCommands, aliases: List<String>) {
        registeredCommands[command.lowercase()] = commandType

        for (alias in aliases) registeredCommands[alias.lowercase()] = commandType
    }
}

