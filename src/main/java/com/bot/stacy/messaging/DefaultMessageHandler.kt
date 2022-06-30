package com.bot.stacy.messaging

import com.bot.stacy.messaging.taskHandlers.PictureSender
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.Update
import org.telegram.telegrambots.meta.bots.AbsSender

// mvncompile
// mvexec:java -Dexec.mainClass=com.bot.stacy.Main

class DefaultMessageHandler(
    private val sender: AbsSender
) : MessageHandler {
    private val pictureSender = PictureSender()

    override fun handleIncomingUpdate(chatUpdate: Update) {
        println("New update: $chatUpdate")

        if (chatUpdate.hasMessage()) {
            val messageText = chatUpdate.message.text

            try {
                when {
                    messageText?.contains("meme") ?: false -> pictureSender.sendMessage(sender, chatUpdate)
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