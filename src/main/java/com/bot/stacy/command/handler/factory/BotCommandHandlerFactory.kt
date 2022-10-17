package com.bot.stacy.command.handler.factory

import com.bot.stacy.ResponseMessageObserver
import com.bot.stacy.command.handler.CommandHandler
import com.bot.stacy.command.handler.JokeCommandHandler
import com.bot.stacy.command.handler.MemeCommandHandler
import com.bot.stacy.common.BotCommand
import com.bot.stacy.model.Command

object BotCommandHandlerFactory : CommandHandlerFactory {

    override fun getCommandHandler(command: Command, responseMessageObserver: ResponseMessageObserver): CommandHandler {
        return when (command.name) {
            BotCommand.MEME -> MemeCommandHandler(responseMessageObserver)
            BotCommand.JOKE -> JokeCommandHandler(responseMessageObserver)
            else -> throw Exception("No handler for command: ${command.name}")
        }
    }
}