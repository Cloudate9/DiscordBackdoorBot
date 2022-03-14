package io.github.cloudate9.discordbackdoorbot.commands

import com.diogonunes.jcolor.Ansi.colorize
import com.diogonunes.jcolor.Attribute
import io.github.cloudate9.discordbackdoorbot.DiscordBackdoorBot

class RevokeRoleCommand(private val discordBackdoorBot: DiscordBackdoorBot) : IBackdoorBotCommands {

    override fun onCommand() {

        println(colorize("Operation: Remove a role from a player.", Attribute.CYAN_TEXT()))

        val userId = promptUserId()

        val user = discordBackdoorBot.discordBotImplementation.getUserById(userId).get()

        if (user == null) {
            println(
                colorize(
                    "The user with the id \"$userId\" does not exist in this server! Cancelling removal of role...",
                    Attribute.RED_TEXT()
                )
            )
            return
        }

        val role = discordBackdoorBot.promptUserForExistingServerRole() ?: return

        discordBackdoorBot.server.removeRoleFromUser(user, role)

        println(
            colorize(
                "The \"${role.name}\" role has been revoked from the user \"${user.discriminatedName}\" !",
                Attribute.GREEN_TEXT()
            )
        )


    }

    private fun promptUserId(): Long {
        println(colorize("Enter the id of the user you want to remove the role from.", Attribute.CYAN_TEXT()))
        print("> ")

        return InputUtils.inputNaturalNumberCheck(readLine()) ?: promptUserId()
    }
}