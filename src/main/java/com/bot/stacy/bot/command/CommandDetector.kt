package com.bot.stacy.bot.command

import com.bot.stacy.model.Command
import org.telegram.telegrambots.meta.api.objects.Message

interface CommandDetector {
    fun processMessage(message: Message)
}

class BotCommandDetector(
    private val botName: String,
    private val commandListener: CommandListener
) : CommandDetector {

    override fun processMessage(message: Message) {
        commandListener.onCommand(
            Command(
                chatId = message.chatId,
                command = "new_command".lowercase()
            )
        )
    }
}