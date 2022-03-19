package com.bot.stacy.messaging

import org.telegram.telegrambots.meta.api.objects.Update

interface MessageHandler {
    fun handleIncomingUpdate(chatUpdate: Update)
}