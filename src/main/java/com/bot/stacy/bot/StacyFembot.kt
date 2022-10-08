package com.bot.stacy.bot

import com.bot.stacy.bot.command.CommandListener
import com.bot.stacy.repository.config.BotConfigRepository
import com.bot.stacy.repository.config.DefaultBotConfigRepository
import org.telegram.telegrambots.bots.TelegramLongPollingBot
import org.telegram.telegrambots.meta.api.methods.botapimethods.BotApiMethodMessage
import org.telegram.telegrambots.meta.api.objects.Update

class StacyFembot(
    private val configRepository: BotConfigRepository = DefaultBotConfigRepository(),
    // private val messageHandler: MessageHandler
) : TelegramLongPollingBot(), CommandListener {

    override fun getBotToken() = configRepository.botToken
    override fun getBotUsername() = configRepository.botUsername

    override fun onUpdateReceived(chatUpdate: Update) {
        if (chatUpdate.hasMessage()) {
            chatUpdate.message.text
        }
        // messageHandler.handleIncomingUpdate(chatUpdate)
    }

    override fun onCommand(command: String) {

    }

    fun onResponseMessagePrepared(message: BotApiMethodMessage) {
        execute(message)
    }
}