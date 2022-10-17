package com.bot.stacy.command.handler.factory

import com.bot.stacy.ResponseMessageObserver
import com.bot.stacy.command.handler.CommandHandler
import com.bot.stacy.command.handler.MemeCommandHandler
import com.bot.stacy.common.CommandName
import com.bot.stacy.model.Command

object BotCommandHandlerFactory : CommandHandlerFactory {

    override fun getCommandHandler(command: Command, responseMessageObserver: ResponseMessageObserver): CommandHandler {
        return when (command.name) {
            CommandName.MEME -> MemeCommandHandler(responseMessageObserver)
            else -> throw Exception("No handler for command: ${command.name}")
        }
    }
}