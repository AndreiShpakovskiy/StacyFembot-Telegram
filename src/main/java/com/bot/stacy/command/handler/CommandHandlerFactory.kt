package com.bot.stacy.command.handler

import com.bot.stacy.ResponseMessageObserver
import com.bot.stacy.model.Command

interface CommandHandlerFactory {
    fun getCommandHandler(command: Command, responseMessageObserver: ResponseMessageObserver): CommandHandler
}