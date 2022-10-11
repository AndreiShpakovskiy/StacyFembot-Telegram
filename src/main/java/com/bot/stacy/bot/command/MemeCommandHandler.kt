package com.bot.stacy.bot.command

import com.bot.stacy.bot.ResponseMessageObserver
import com.bot.stacy.model.Command
import org.telegram.telegrambots.meta.api.methods.send.SendMessage

class MemeCommandHandler(
    private val responseMessageObserver: ResponseMessageObserver
) : CommandHandler {

    override fun handleCommand(command: Command) {
        val message = SendMessage()
        message.text = command.command
        message.chatId = "${command.chatId}"

        responseMessageObserver.onResponsePrepared(message)
    }
}