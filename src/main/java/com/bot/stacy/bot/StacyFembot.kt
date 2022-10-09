package com.bot.stacy.bot

import com.bot.stacy.bot.command.*
import com.bot.stacy.model.Command
import com.bot.stacy.repository.config.BotConfigRepository
import com.bot.stacy.repository.config.DefaultBotConfigRepository
import org.telegram.telegrambots.bots.TelegramLongPollingBot
import org.telegram.telegrambots.meta.api.methods.botapimethods.BotApiMethodMessage
import org.telegram.telegrambots.meta.api.objects.Update

class StacyFembot(
    private val configRepository: BotConfigRepository = DefaultBotConfigRepository(),
) : TelegramLongPollingBot(), CommandListener, ResponseMessageObserver {
    private val commandDetector: CommandDetector
    private val commandHandler: CommandHandler

    init {
        commandDetector = BotCommandDetector(botUsername, this)
        commandHandler = MemeCommandHandler(responseMessageObserver = this)
    }

    override fun getBotToken() = configRepository.botToken
    override fun getBotUsername() = configRepository.botUsername

    override fun onUpdateReceived(chatUpdate: Update) {
        println("New update: $chatUpdate")

        if (chatUpdate.hasMessage() && chatUpdate.message.hasText()) {
            commandDetector.processMessage(chatUpdate.message)
        }
    }

    override fun onCommand(command: Command) {
        println("New command: $command")

        commandHandler.handleCommand(command)
    }

    override fun onResponsePrepared(message: BotApiMethodMessage) {
        println("On new response $message")
        execute(message)
    }
}