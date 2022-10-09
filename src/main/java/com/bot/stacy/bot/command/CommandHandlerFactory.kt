package com.bot.stacy.bot.command

import com.bot.stacy.model.Command

interface CommandHandlerFactory {
    fun getCommandHandler(command: Command): CommandHandler
}

//object BotCommandResolverFactory : CommandResolverFactory {
//    override fun getCommandResolver(command: String): CommandResolver {
//    }
//}