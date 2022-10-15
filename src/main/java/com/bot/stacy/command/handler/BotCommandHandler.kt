package com.bot.stacy.command.handler

import com.bot.stacy.ResponseMessageObserver
import com.bot.stacy.model.Command

class BotCommandHandler(
    private val responseMessageObserver: ResponseMessageObserver
) : CommandHandler {

    override fun handleCommand(command: Command) {
        BotCommandHandlerFactory.getCommandHandler(command, responseMessageObserver).handleCommand(command)
    }
}