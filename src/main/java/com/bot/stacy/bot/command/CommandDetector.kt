package com.bot.stacy.bot.command

import com.bot.stacy.model.Command
import org.telegram.telegrambots.meta.api.objects.Message

interface CommandDetector {
    fun processMessage(message: Message)
}

class BotCommandDetector(
    botName: String,
    private val commandListener: CommandListener
) : CommandDetector {
    private val commandRegex = Regex("^/([a-zA-Z0-9]+)(@$botName)?", RegexOption.IGNORE_CASE)

    override fun processMessage(message: Message) {

        // If it matches regex, there are 3 groups
        if (message.hasText() && message.text.matches(commandRegex)) {
            commandListener.onCommand(
                Command(
                    chatId = message.chatId,
                    command = "${commandRegex.find(message.text)?.groupValues?.get(1)}".lowercase()
                )
            )
        }
    }
}