package com.bot.stacy

import com.bot.stacy.ResponseMessageObserver
import com.bot.stacy.command.detector.BotCommandDetector
import com.bot.stacy.command.detector.CommandDetector
import com.bot.stacy.command.handler.BotCommandHandler
import com.bot.stacy.command.handler.CommandHandler
import com.bot.stacy.command.detector.CommandListener
import com.bot.stacy.model.Command
import com.bot.stacy.repository.config.BotConfigRepository
import com.bot.stacy.repository.config.DefaultBotConfigRepository
import org.telegram.telegrambots.bots.TelegramLongPollingBot
import org.telegram.telegrambots.meta.api.methods.botapimethods.BotApiMethodMessage
import org.telegram.telegrambots.meta.api.objects.Update

// mvncompile
// mvexec:java -Dexec.mainClass=com.bot.stacy.Main

class StacyFembot(
    private val configRepository: BotConfigRepository = DefaultBotConfigRepository(),
) : TelegramLongPollingBot(), CommandListener, ResponseMessageObserver {
    private val commandDetector: CommandDetector
    private val commandHandler: CommandHandler

    init {
        commandDetector = BotCommandDetector(botUsername, this)
        commandHandler = BotCommandHandler(responseMessageObserver = this)
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