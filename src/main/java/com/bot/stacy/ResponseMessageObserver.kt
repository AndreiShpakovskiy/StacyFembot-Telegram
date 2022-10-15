package com.bot.stacy

import org.telegram.telegrambots.meta.api.methods.botapimethods.BotApiMethodMessage

interface ResponseMessageObserver {
    fun onResponsePrepared(message: BotApiMethodMessage)
}