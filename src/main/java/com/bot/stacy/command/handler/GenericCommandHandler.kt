package com.bot.stacy.command.handler

import com.bot.stacy.ResponseMessageObserver
import com.bot.stacy.command.handler.factory.BotCommandHandlerFactory
import com.bot.stacy.model.Command
import org.telegram.telegrambots.meta.api.methods.send.SendMessage

class GenericCommandHandler(
    private val responseMessageObserver: ResponseMessageObserver
) : CommandHandler {

    override fun handleCommand(command: Command) {
        try {
            BotCommandHandlerFactory
                .getCommandHandler(command, responseMessageObserver)
                .handleCommand(command)
        } catch (e: Exception) {
            e.printStackTrace()
            sendUnsupportedCommandResponse(command)
        }
    }

    private fun sendUnsupportedCommandResponse(command: Command) {
        val responseMessage = SendMessage()
        responseMessage.chatId = "${command.chatId}"
        responseMessage.text = "Command \"${command.name}\" is not supported yet"

        responseMessageObserver.onResponsePrepared(responseMessage)
    }
}