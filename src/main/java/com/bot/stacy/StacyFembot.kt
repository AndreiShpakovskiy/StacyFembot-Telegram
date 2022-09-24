package com.bot.stacy

import com.bot.stacy.messaging.DefaultMessageHandler
import com.bot.stacy.repository.config.DefaultBotConfigRepository
import org.telegram.telegrambots.bots.TelegramLongPollingBot
import org.telegram.telegrambots.meta.api.objects.Update

class StacyFembot : TelegramLongPollingBot() {
    private val configRepository = DefaultBotConfigRepository()
    private val defaultMessageHandler = DefaultMessageHandler(this, botUsername ?: "")

    override fun getBotToken() = configRepository.botToken
    override fun getBotUsername() = configRepository.botUsername

    override fun onUpdateReceived(chatUpdate: Update) {
        defaultMessageHandler.handleIncomingUpdate(chatUpdate)
    }
}