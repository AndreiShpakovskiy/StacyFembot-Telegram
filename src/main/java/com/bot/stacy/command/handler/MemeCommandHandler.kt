package com.bot.stacy.command.handler

import com.bot.stacy.ResponseMessageObserver
import com.bot.stacy.model.Command
import org.telegram.telegrambots.meta.api.methods.send.SendMessage

class MemeCommandHandler(private val responseMessageObserver: ResponseMessageObserver) : CommandHandler {

    override fun handleCommand(command: Command) {
        val message = SendMessage()
        message.text = "There should be a new meme"
        message.chatId = "${command.chatId}"
        responseMessageObserver.onResponsePrepared(message)
    }
}