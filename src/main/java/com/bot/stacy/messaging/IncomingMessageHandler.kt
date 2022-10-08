package com.bot.stacy.messaging

import com.bot.stacy.common.BotCommand
import com.bot.stacy.messaging.taskHandlers.DefaultMemeSender
import com.bot.stacy.messaging.taskHandlers.MemeSender
import org.telegram.telegrambots.meta.api.objects.Update
import org.telegram.telegrambots.meta.bots.AbsSender

// mvncompile
// mvexec:java -Dexec.mainClass=com.bot.stacy.Main

class IncomingMessageHandler(
    private val sender: AbsSender,
    private val botUsername: String,
    private val memeSender: MemeSender = DefaultMemeSender()
) : MessageHandler {

    override fun handleIncomingUpdate(chatUpdate: Update) {
        println("New update: $chatUpdate")

        if (chatUpdate.hasMessage()) {
            val messageText = chatUpdate.message.text

            try {
                when {
                    messageText?.matches(
                        Regex(
                            "^/?${BotCommand.Meme.ANY}(@$botUsername)?\\s*",
                            RegexOption.IGNORE_CASE
                        )
                    ) ?: false -> memeSender.send(BotCommand.Meme.ANY, sender, chatUpdate)
                    messageText?.matches(
                        Regex(
                            "^/?${BotCommand.Meme.ANIME}(@$botUsername)?\\s*",
                            RegexOption.IGNORE_CASE
                        )
                    ) ?: false -> memeSender.send(BotCommand.Meme.ANIME, sender, chatUpdate)
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}