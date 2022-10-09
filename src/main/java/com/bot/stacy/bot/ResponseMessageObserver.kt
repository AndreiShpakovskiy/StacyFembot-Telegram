package com.bot.stacy.bot

import org.telegram.telegrambots.meta.api.methods.botapimethods.BotApiMethodMessage

interface ResponseMessageObserver {
    fun onResponsePrepared(message: BotApiMethodMessage)
}