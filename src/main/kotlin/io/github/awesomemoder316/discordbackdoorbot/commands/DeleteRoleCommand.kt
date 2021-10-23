package io.github.awesomemoder316.discordbackdoorbot.commands

import com.diogonunes.jcolor.Ansi.colorize
import com.diogonunes.jcolor.Attribute
import io.github.awesomemoder316.discordbackdoorbot.DiscordBackdoorBot

class DeleteRoleCommand(private val discordBackdoorBot: DiscordBackdoorBot): IBackdoorBotCommands {

    /**
     * Unlike CreateRoleCommand where the user is prompted to re-input when an input is invalid,
     * DeleteRoleCommand opts to cancel the operation, as deleting is more destructive.
     */
    override fun onCommand() {

        println(colorize("Operation: Delete existing role.", Attribute.CYAN_TEXT()))

        val selectedRoleToDelete = discordBackdoorBot.promptUserForExistingServerRole() ?: return

        if (!confirmDeletion(selectedRoleToDelete.name)) {
            println(colorize("Role deletion cancelled by user.", Attribute.RED_TEXT()))
            return
        }

        selectedRoleToDelete.delete().get()

        println(colorize("Role \"${selectedRoleToDelete.name}\" removed successfully.", Attribute.GREEN_TEXT()))

    }

    /**
     * Promts if the user does indeed want to delete the selected role.
     */
    private fun confirmDeletion(roleName: String): Boolean {
        println(colorize("Please confirm that you want to delete the role \"$roleName\" (Y/N): ",
            Attribute.CYAN_TEXT()))
        print("> ")
        return InputUtils.inputBooleanCheck(readLine()) ?: confirmDeletion(roleName)
    }


}
