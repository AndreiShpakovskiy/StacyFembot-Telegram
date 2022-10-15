package com.bot.stacy.bot.command

import com.bot.stacy.bot.ResponseMessageObserver
import com.bot.stacy.model.Command

interface CommandHandlerFactory {
    fun getCommandHandler(command: Command, responseMessageObserver: ResponseMessageObserver): CommandHandler
}