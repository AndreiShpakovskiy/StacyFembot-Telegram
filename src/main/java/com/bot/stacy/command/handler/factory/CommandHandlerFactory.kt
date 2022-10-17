package com.bot.stacy.command.handler.factory

import com.bot.stacy.ResponseMessageObserver
import com.bot.stacy.command.handler.CommandHandler
import com.bot.stacy.model.Command

interface CommandHandlerFactory {
    fun getCommandHandler(command: Command, responseMessageObserver: ResponseMessageObserver): CommandHandler
}