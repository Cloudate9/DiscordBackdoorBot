package io.github.cloudate9.discordbackdoorbot.commands

import com.diogonunes.jcolor.Ansi.colorize
import com.diogonunes.jcolor.Attribute

class HelpCommand : IBackdoorBotCommands {

    override fun onCommand() {
        println(colorize("Operation: Display all commands.", Attribute.CYAN_TEXT()))

        //Purposeful lack of colorization for help command, as looking at a chunk of text in one colour is tiring.
        println("CreateRole (aliases: add, addrole, create, newrole): Creates a new role for your Discord server.")
        println("DeleteRole (aliases: delete, remove, removerole): Deletes a role from your Discord server.")
        println("GiveRole (aliases: assign, assignrole, give, promote, promoterole): Gives a role to a user or a bot.")
        println("Help: This command.")
        println("Invite (aliases: link): Provides you a link to invite the bot to your server with the appropriate permissions.")
        println("ListRoles (aliases: list, roles): List all the roles in your Discord server.")
        println("RevokeRole (aliases: demote, demoterole, revoke): Revoke a role from a user or a bot.")
        println("setup (aliases: setupassistant): Setup assistant for Discord bot token and server id input.")
        println("stop (aliases: end, exit, quit): Stops the program.")
    }
}
