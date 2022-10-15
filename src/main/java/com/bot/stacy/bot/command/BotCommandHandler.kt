package com.bot.stacy.bot.command

import com.bot.stacy.bot.ResponseMessageObserver
import com.bot.stacy.model.Command

class BotCommandHandler(
    private val responseMessageObserver: ResponseMessageObserver
) : CommandHandler {

    override fun handleCommand(command: Command) {
        BotCommandHandlerFactory.getCommandHandler(command, responseMessageObserver).handleCommand(command)
    }
}