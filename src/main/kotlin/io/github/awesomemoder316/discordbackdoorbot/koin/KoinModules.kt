package io.github.awesomemoder316.discordbackdoorbot.koin

import io.github.awesomemoder316.discordbackdoorbot.DiscordBackdoorBot
import io.github.awesomemoder316.discordbackdoorbot.commands.*
import io.github.awesomemoder316.discordbackdoorbot.commands.sorter.CommandSorter
import org.javacord.api.entity.server.Server
import org.koin.dsl.factory
import org.koin.dsl.module
import org.koin.dsl.single

@kotlinx.serialization.ExperimentalSerializationApi
val commandsModule = module() {
    single<CommandSorter>()

    //It doesn't matter that these are factory, cause a single instance will be stored by singleton CommandSorter.
    factory<CreateRoleCommand>()
    factory<DeleteRoleCommand>()
    factory<GiveRoleCommand>()
    factory<HelpCommand>()
    factory<InviteCommand>()
    factory<ListServerRolesCommand>()
    factory<RevokeRoleCommand>()
    factory<SetupCommand>()
    factory<StopCommand>()
}

val discordBackdoorBotModule = module {
    single<DiscordBackdoorBot>()
}

