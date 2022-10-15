package com.bot.stacy.bot.command

import com.bot.stacy.bot.ResponseMessageObserver
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