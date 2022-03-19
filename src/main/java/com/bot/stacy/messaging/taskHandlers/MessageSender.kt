package com.bot.stacy.messaging.taskHandlers

import org.telegram.telegrambots.meta.api.objects.Update
import org.telegram.telegrambots.meta.bots.AbsSender

interface MessageSender {
    fun sendMessage(sender: AbsSender, chatUpdate: Update)
}