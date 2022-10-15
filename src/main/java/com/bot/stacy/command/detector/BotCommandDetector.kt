package com.bot.stacy.command.detector

import com.bot.stacy.model.Command
import org.telegram.telegrambots.meta.api.objects.Message

class BotCommandDetector(
    botName: String,
    commandListener: CommandListener
) : CommandDetector(commandListener) {
    private val commandRegex = Regex("^/([a-zA-Z0-9]+)(@$botName)?", RegexOption.IGNORE_CASE)

    override fun processMessage(message: Message) {

        // If it matches regex, there are 3 groups
        if (message.hasText() && message.text.matches(commandRegex)) {
            commandListener.onCommand(
                Command(
                    chatId = message.chatId,
                    name = "${commandRegex.find(message.text)?.groupValues?.get(1)}".lowercase()
                )
            )
        }
    }
}