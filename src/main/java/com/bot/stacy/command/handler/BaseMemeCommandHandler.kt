package com.bot.stacy.command.handler

import com.bot.stacy.ResponseMessageObserver
import org.telegram.telegrambots.meta.api.methods.send.SendMessage

abstract class BaseMemeCommandHandler(
    protected val responseMessageObserver: ResponseMessageObserver
) : CommandHandler {

    protected fun sendInitialMessage(chatId: String) {
        val message = SendMessage()
        message.chatId = chatId
        message.text = "Looking for a meme for you"
        responseMessageObserver.onResponsePrepared(message)
    }
}