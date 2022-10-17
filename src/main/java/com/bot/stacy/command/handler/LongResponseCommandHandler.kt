package com.bot.stacy.command.handler

import com.bot.stacy.ResponseMessageObserver
import org.telegram.telegrambots.meta.api.methods.send.SendMessage

/**
 * "Long response" means, that handler should perform some additional requests or perform some operations before
 * final result can be provided.
 *
 * It means, that some "instant" message should be sent first to let the user know, that command was received
 * successfully and will be processed as soon as possible.
 */
abstract class LongResponseCommandHandler(
    protected val responseMessageObserver: ResponseMessageObserver
) : CommandHandler {

    protected abstract val initialMessageText: String

    protected fun sendInitialMessage(chatId: String) {
        val message = SendMessage()
        message.chatId = chatId
        message.text = initialMessageText
        responseMessageObserver.onResponsePrepared(message)
    }
}