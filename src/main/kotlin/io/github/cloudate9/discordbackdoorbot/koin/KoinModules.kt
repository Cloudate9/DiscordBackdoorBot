package io.github.cloudate9.discordbackdoorbot.koin

import io.github.cloudate9.discordbackdoorbot.DiscordBackdoorBot
import io.github.cloudate9.discordbackdoorbot.commands.sorter.CommandSorter
import io.github.cloudate9.discordbackdoorbot.commands.*
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

@kotlinx.serialization.ExperimentalSerializationApi
val commandsModule = module {
    singleOf(::CommandSorter)

    //It doesn't matter that these are factory, cause a single instance will be stored by singleton CommandSorter.
    factoryOf(::CreateRoleCommand)
    factoryOf(::DeleteRoleCommand)
    factoryOf(::GiveRoleCommand)
    factoryOf(::HelpCommand)
    factoryOf(::InviteCommand)
    factoryOf(::ListServerRolesCommand)
    factoryOf(::RevokeRoleCommand)
    factoryOf(::SetupCommand)
    factoryOf(::StopCommand)
}

val discordBackdoorBotModule = module {
    singleOf(::DiscordBackdoorBot)
}

