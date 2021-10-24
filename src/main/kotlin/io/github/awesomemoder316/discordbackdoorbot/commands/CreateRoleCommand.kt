package io.github.awesomemoder316.discordbackdoorbot.commands

import com.diogonunes.jcolor.Ansi.colorize
import com.diogonunes.jcolor.Attribute
import io.github.awesomemoder316.discordbackdoorbot.DiscordBackdoorBot
import org.javacord.api.entity.permission.Permissions
import org.javacord.api.entity.permission.Role
import org.javacord.api.entity.permission.RoleBuilder

class CreateRoleCommand(private val discordBackdoorBot: DiscordBackdoorBot): IBackdoorBotCommands {


    override fun onCommand() {
        println(colorize("Operation: Create new role.", Attribute.CYAN_TEXT()))
        val roleName = promptRoleName() ?: return //Null means that user no longer wants to create role.
        val displaySeparately = promptDisplaySeparately()
        val mentionableByOtherRoles = promptMentionableByOtherRoles()
        val permissionNumber = promptPermissionNumber()

        val confirmCreation = confirmCreation(
                roleName,
                displaySeparately,
                mentionableByOtherRoles,
                permissionNumber
            )

        if (!confirmCreation) {
            println(colorize("Role creation terminated.", Attribute.RED_TEXT()))
            return
        }

        //Create new role.
        RoleBuilder(discordBackdoorBot.server)
            .setDisplaySeparately(displaySeparately)
            .setMentionable(mentionableByOtherRoles)
            .setName(roleName)
            .setPermissions(Permissions.fromBitmask(permissionNumber))
            .create()
            .get()

        println(colorize("Role \"$roleName\" created successfully!", Attribute.GREEN_TEXT()))

    }

    /**
     * Checks if the proposed role name is a duplicate, and prompts for a new name if a duplicate exist.
     * @return The altered role name, or the original role name if unaltered.
     */
    private fun checkDuplicateRoleName(originalRoleName: String, roles: Collection<Role>): String? {
        for (role in roles) {
            if (role.name == originalRoleName) {

                //Duplicate found. Check if user really wants to continue with duplicate
                val newRoleName = duplicateFound(originalRoleName)

                //New name given. Check if name is a duplicate.
                if (originalRoleName != originalRoleName) return checkDuplicateRoleName(originalRoleName, roles)

                return newRoleName
            }
        }
        return originalRoleName
    }

    /**
     * Prompt for confirmation of bot creation.
     */
    private fun confirmCreation(roleName: String, displaySeparately: Boolean,
                                mentionableByOtherRoles: Boolean, permissionNumber: Int):Boolean {
        println(
            colorize("Please confirm that you want to create a role with the following specification (Y/N): \n" +
                "Role name: $roleName\n" +
                "Display separately from other roles: $displaySeparately\n" +
                "Mentionable by other roles: $mentionableByOtherRoles\n" +
                "Permission number for the role: $permissionNumber",
                Attribute.CYAN_TEXT())
        )

        print("> ")

        return InputUtils.inputBooleanCheck(readLine())
            ?: confirmCreation(roleName, displaySeparately, mentionableByOtherRoles, permissionNumber)
    }

    /**
     * Called when a duplicate is found, and prompts for a change.
     * @return The new/existing name of the role if valid, or null if operation is to be cancelled.
     */
    private fun duplicateFound(originalName: String): String? {
        println(
            colorize("Another role with the same name has been found!\n" +
                "It is strongly recommended that you change the name of the role (ChangeName/CancelCreation/Ignore):",
            Attribute.RED_TEXT()
            )
        )
        print("> ")

        val optionChosen = InputUtils.inputStringCheck(readLine()) ?: return duplicateFound(originalName)

        return when (optionChosen.lowercase()) {
            "changename", "change", "name" -> {

                val newName = InputUtils.inputStringCheck(readLine()) ?: return duplicateFound(originalName)
                
                println(colorize("Role name set to $newName.", Attribute.GREEN_TEXT()))
                newName.lowercase()
            }

            "cancelcreation", "cancelrole", "cancel" -> {
                println(colorize("Role creation cancelled.", Attribute.RED_TEXT()))
                null
            }

            "ignore" -> {
                println(colorize("Role name unchanged.", Attribute.GREEN_TEXT()))
                originalName
            }

            else -> {
                println(
                    colorize(
                        "Your input of \"$optionChosen\" is invalid! Please enter \"changeName\" to change the name of the role," +
                        "\"cancelCreation\" to cancel the creation of the role, or \"ignore\" to continue with the duplicate role name.",
                    Attribute.RED_TEXT()
                    )
                )

                duplicateFound(originalName)
            }
        }
    }

    /**
     * Prompts if the role should be displayed separately from other roles
     * @return True if true, or false if false.
     */
    private fun promptDisplaySeparately(): Boolean {
        println(colorize("Enter if you want the role to be displayed separately from other roles (Y/N):", Attribute.CYAN_TEXT()))
        print("> ")

        return InputUtils.inputBooleanCheck(readLine()) ?: promptDisplaySeparately()
    }

    /**
     * Prompts if role should be mentionable.
     * @return True if true, and false if false.
     */
    private fun promptMentionableByOtherRoles(): Boolean {
        println(colorize("Enter if you want the role to be mentionable to others (Y/N):", Attribute.CYAN_TEXT()))
        print("> ")

        return InputUtils.inputBooleanCheck(readLine()) ?: return promptMentionableByOtherRoles()
    }

    /**
     * Prompts for the permission number of the bot. Does NOT check if the permission number is valid.
     * @return The permission number.
     */
    private fun promptPermissionNumber(): Int {
        println(
            colorize("Permissions are assigned based on a discord number thing. 8 means administrator. " +
                "Refer to here to get the permission number suitable for you: " +
                "https://discordapi.com/permissions.html#0",
                Attribute.CYAN_TEXT()
            )
        )
        print("> ")

        return InputUtils.inputWholeNumberCheck(readLine())?.toInt() ?: promptPermissionNumber()
    }

    /**
     * Prompts what the role nome should be. 
     * @return The role name.
     */
    private fun promptRoleName(): String? {
        println(colorize("Enter your role name:", Attribute.CYAN_TEXT()))
        print("> ")

        val result = InputUtils.inputStringCheck(readLine())

        if (result != null)

            return checkDuplicateRoleName(result, discordBackdoorBot.server.roles)

        return promptRoleName()
    }

}
