package io.github.cloudate9.discordbackdoorbot

import com.diogonunes.jcolor.Ansi.colorize
import com.diogonunes.jcolor.Attribute
import io.github.cloudate9.discordbackdoorbot.commands.sorter.CommandSorter
import io.github.cloudate9.discordbackdoorbot.json.ConfigJson
import io.github.cloudate9.discordbackdoorbot.koin.commandsModule
import io.github.cloudate9.discordbackdoorbot.koin.discordBackdoorBotModule
import io.github.cloudate9.discordbackdoorbot.commands.*
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromStream
import org.javacord.api.DiscordApi
import org.javacord.api.DiscordApiBuilder
import org.javacord.api.entity.permission.Role
import org.javacord.api.entity.server.Server
import org.koin.core.component.KoinComponent
import org.koin.core.component.get
import org.koin.core.component.inject
import org.koin.core.context.startKoin
import java.io.IOException
import java.io.InputStream
import java.nio.file.Files
import java.nio.file.Path
import java.util.concurrent.CompletionException
import kotlin.io.path.*
import kotlin.system.exitProcess

class DiscordBackdoorBot : KoinComponent {

    val commandSorter by inject<CommandSorter>()
    val configPath: Path

    private lateinit var configDetails: ConfigJson
    lateinit var discordBotImplementation: DiscordApi
    lateinit var server: Server


    companion object : KoinComponent {
        val discordBackdoorBot by inject<DiscordBackdoorBot>() //Retrieve a singleton instance of this class.
    }

    /**
     * Define config.
     */
    init {
        val jarDirPath = Path.of(this::class.java.protectionDomain.codeSource.location.path).parent
        //Get parent as the orig path is to the jar file. We want the dir that the jar file is in.
        val outputDirPath = Path.of(jarDirPath.toUri().resolve("DiscordBackdoorBotData"))
        configPath = outputDirPath.resolve("config.json")
    }

    /**
     * Creates a connection to the specified Discord Bot. Will cause the program to shut down if bot token is incorrect.
     */
    @kotlinx.serialization.ExperimentalSerializationApi
    fun connectToDiscordBot() {
        val configStream = configPath.inputStream()
        configDetails = Json.decodeFromStream(configStream)

        try {
            discordBotImplementation = DiscordApiBuilder()
                .setToken(configDetails.discordBotToken)
                .login().join()

        } catch (ex: CompletionException) {
            println(
                colorize(
                    "Discord bot token is invalid! Please correct this in your config.json!",
                    Attribute.RED_TEXT()
                )
            )
            println(colorize("Program is now shutting down...", Attribute.RED_TEXT()))
            exitProcess(0)
        }

        try {
            server = discordBotImplementation.getServerById(configDetails.serverId)
                .get() //Will throw give error if incorrect.
        } catch (ex: NoSuchElementException) {
            println(
                colorize(
                    "Discord server id is invalid! Please correct this in your config.json!",
                    Attribute.RED_TEXT()
                )
            )
            println(colorize("Program is now shutting down...", Attribute.RED_TEXT()))
            exitProcess(0)
        }

        println(
            colorize(
                "Beep beep! \"${
                    discordBotImplementation.yourself.name
                }\" has been successfully started in the server \"${
                    server.name
                }\".",
                Attribute.GREEN_TEXT()
            )
        )
    }

    /**
     * Copies a file from resources to a specified directory.
     * @return True if the process was successful, false otherwise.
     */
    private fun copyConfig(): Boolean {

        val inputStream = getConfigInputStream() ?: return false

        val fileContents = String(inputStream.readAllBytes())

        val outputFile = configPath

        return try {
            outputFile.apply { parent?.createDirectories() }
                .writeText(fileContents)
            true
        } catch (ex: IOException) {
            println(
                colorize(
                    "Config file is unable to be copied! Please move DiscordBackdoorBot.jar to another location," +
                            " where administrator/root access is not needed to create files.", Attribute.RED_TEXT()
                )
            )
            false
        }
    }

    /**
     * Get the config via input stream.
     */
    private fun getConfigInputStream(): InputStream? {
        val inputStream = this.javaClass.classLoader.getResourceAsStream("config.json")

        if (inputStream == null) {//Shouldn't happen
            println(
                colorize(
                    "Error! Configuration file copying failed. Please open an issue on Github.",
                    Attribute.RED_TEXT()
                )
            )
            return null
        }

        return inputStream
    }

    /**
     * Register all terminal commands.
     */
    fun registerCommands() {
        commandSorter.registerCommand(
            "createrole",
            get<CreateRoleCommand>(),
            listOf("add", "addrole", "create", "newrole")
        )
        commandSorter.registerCommand("deleterole", get<DeleteRoleCommand>(), listOf("delete", "remove", "removerole"))
        commandSorter.registerCommand(
            "giverole",
            get<GiveRoleCommand>(),
            listOf("assign", "assignrole", "give", "promote", "promoterole")
        )
        commandSorter.registerCommand("help", get<HelpCommand>())
        commandSorter.registerCommand("invite", get<InviteCommand>(), listOf("link"))
        commandSorter.registerCommand("listroles", get<ListServerRolesCommand>(), listOf("list", "roles"))
        commandSorter.registerCommand("revokerole", get<RevokeRoleCommand>(), listOf("demote", "demoterole", "revoke"))
        commandSorter.registerCommand("setup", get<SetupCommand>(), listOf("setupAssistant"))
        commandSorter.registerCommand("stop", get<StopCommand>(), listOf("end", "exit", "quit"))
    }

    /**
     * Ask a user for the name of a role. Used by promptUserForExistingServerRole()
     */
    private fun promptRoleName(): String {
        println(
            colorize(
                "Enter the role name you want to use for this operation. Roles are case sensitive.",
                Attribute.CYAN_TEXT()
            )
        )
        print("> ")
        return InputUtils.inputStringCheck(readLine()) ?: return promptRoleName()
    }

    /**
     * Get a role from the server, which we can then do different things with.
     * @return A role if successful, null otherwise.
     */
    fun promptUserForExistingServerRole(): Role? {
        val roleName = promptRoleName()

        val rolesIndicated = discordBackdoorBot.server.getRolesByName(roleName)

        if (rolesIndicated.isEmpty()) {
            println(
                colorize(
                    "The role you indicated does not exist! Cancelling operation...",
                    Attribute.RED_TEXT()
                )
            )
            return null
        }

        return if (rolesIndicated.size == 1) rolesIndicated[0]
        else {
            println(
                colorize(
                    "There are more than 1 role that share the indicated name! " +
                            "Please select the appropriate role from the list below, " +
                            "and enter the number of the role.", Attribute.CYAN_TEXT()
                )
            )

            for ((index, role) in rolesIndicated.withIndex()) {
                println("${index + 1}. $role")
            }

            println(colorize("Indicate the number of the role you want to select.", Attribute.CYAN_TEXT()))
            print("> ")
            val selectedNumber = InputUtils.inputNaturalNumberCheck(readLine())

            if (selectedNumber == null || selectedNumber - 1 > rolesIndicated.size) {
                //No need to check if natural number is less than that of map. Will never happen.
                println("The number indicated does not map to any of the above options. Cancelling operation...")
                return null
            }

            rolesIndicated[selectedNumber.toInt() - 1]
        }
    }

    /**
     * @return True if successful, false otherwise.
     */
    fun setupConfig(): Boolean {
        println(
            colorize(
                "Configuration file does not exist! Automatically copying default configuration file...",
                Attribute.CYAN_TEXT()
            )
        )
        Thread.sleep(1000)


        val success = copyConfig()
        if (!success) {
            println(colorize("Discord Backdoor Bot in now shutting down...", Attribute.RED_TEXT()))
            return false
        } else {
            println(
                colorize(
                    "Configuration successfully copied! The config.json can be found at ${configPath.parent.toAbsolutePath()}.",
                    Attribute.GREEN_TEXT()
                )
            )

            setupPrompter()
        }
        return true
    }

    /**
     * Calls the setup command, to automatically set up the bot.
     */
    private fun setupPrompter() {
        commandSorter.callAppropriateCommand("setup")
    }
}

@kotlinx.serialization.ExperimentalSerializationApi
fun main() {

    startKoin {
        modules(commandsModule, discordBackdoorBotModule)
    }

    println(colorize("Checking for configuration file...", Attribute.CYAN_TEXT()))

    Thread.sleep(1000)

    val discordBackdoorBot = DiscordBackdoorBot.discordBackdoorBot //Retrieve instance

    discordBackdoorBot.registerCommands()

    if (!Files.exists(discordBackdoorBot.configPath) ||
        Files.isDirectory(discordBackdoorBot.configPath)
    ) { //Causes file creation even if the path is a dir for some reason.
        if (!discordBackdoorBot.setupConfig()) return
    } else println(colorize("Configuration file found!", Attribute.GREEN_TEXT()))

    discordBackdoorBot.connectToDiscordBot()

    while (true) {
        print("> ") //Not println()

        val input = readLine() ?: continue

        discordBackdoorBot.commandSorter.callAppropriateCommand(input.split(" ")[0])
    }
}
