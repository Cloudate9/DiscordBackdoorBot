package io.github.cloudate9.discordbackdoorbot.commands.sorter

import io.github.cloudate9.discordbackdoorbot.commands.IBackdoorBotCommands

interface ICommandSorter {
    /**
     * This gets method checks if the command is registered, and if so, will call it.
     */
    fun callAppropriateCommand(command: String)

    /**
     * This registers a command without aliases.
     */
    fun registerCommand(command: String, commandType: IBackdoorBotCommands) {
        registerCommand(command, commandType, listOf())
    }

    /**
     * This registers a command with aliases.
     */
    fun registerCommand(command: String, commandType: IBackdoorBotCommands, aliases: List<String>)
}
