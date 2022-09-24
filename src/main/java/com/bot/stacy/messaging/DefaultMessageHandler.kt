package com.bot.stacy.messaging

import com.bot.stacy.common.BotCommand
import com.bot.stacy.messaging.taskHandlers.DefaultMemeSender
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.Update
import org.telegram.telegrambots.meta.bots.AbsSender

// mvncompile
// mvexec:java -Dexec.mainClass=com.bot.stacy.Main

class DefaultMessageHandler(
    private val sender: AbsSender,
    private val botUsername: String
) : MessageHandler {
    private val defaultMemeSender = DefaultMemeSender()

    override fun handleIncomingUpdate(chatUpdate: Update) {
        println("New update: $chatUpdate")

        if (chatUpdate.hasMessage()) {
            val messageText = chatUpdate.message.text

            try {
                when {
                    messageText?.matches(Regex("^/?${BotCommand.Meme.ANY}(@$botUsername)?\\s*", RegexOption.IGNORE_CASE))
                        ?: false -> defaultMemeSender.send(BotCommand.Meme.ANY, sender, chatUpdate)
                    messageText?.matches(Regex("^/?${BotCommand.Meme.ANIME}(@$botUsername)?\\s*", RegexOption.IGNORE_CASE))
                        ?: false -> defaultMemeSender.send(BotCommand.Meme.ANIME, sender, chatUpdate)
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        } else {
            try {
                SendMessage().apply {
                    this.chatId = chatUpdate.myChatMember.chat.id.toString()
                    this.text = "Всем привет 😉"
                    sender.execute(this)
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}