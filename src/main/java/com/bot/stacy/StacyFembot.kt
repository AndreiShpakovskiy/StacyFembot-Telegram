package com.bot.stacy

import com.bot.stacy.command.detector.BotCommandDetector
import com.bot.stacy.command.detector.CommandDetector
import com.bot.stacy.command.detector.CommandListener
import com.bot.stacy.command.handler.GenericCommandHandler
import com.bot.stacy.command.handler.CommandHandler
import com.bot.stacy.model.Command
import com.bot.stacy.repository.config.BotConfigRepository
import com.bot.stacy.repository.config.DefaultBotConfigRepository
import org.telegram.telegrambots.bots.TelegramLongPollingBot
import org.telegram.telegrambots.meta.api.methods.BotApiMethod
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto
import org.telegram.telegrambots.meta.api.objects.Update
import java.io.Serializable

// mvncompile
// mvexec:java -Dexec.mainClass=com.bot.stacy.Main

class StacyFembot(
    private val configRepository: BotConfigRepository = DefaultBotConfigRepository(),
) : TelegramLongPollingBot(), CommandListener, ResponseMessageObserver {
    private val commandDetector: CommandDetector
    private val commandHandler: CommandHandler

    init {
        commandDetector = BotCommandDetector(botUsername, this)
        commandHandler = GenericCommandHandler(responseMessageObserver = this)
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

    override fun <T : Serializable?, Method : BotApiMethod<T>?> onResponsePrepared(message: Method) {
        println("On new response $message")
        execute(message)
    }

    override fun onResponsePrepared(message: SendPhoto) {
        println("On new response $message")
        execute(message)
    }
}