package com.bot.stacy.messaging

import org.telegram.telegrambots.meta.api.methods.send.SendDice
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.Update
import org.telegram.telegrambots.meta.bots.AbsSender

class DefaultMessageHandler(
    private val sender: AbsSender
) : MessageHandler {

    override fun handleIncomingUpdate(chatUpdate: Update) {
        println("New update: $chatUpdate")

        if (chatUpdate.hasMessage() && chatUpdate.message != null) {
            if (chatUpdate.message.newChatMembers.isEmpty()) {
                try {
                    SendMessage().apply {
                        this.chatId = chatUpdate.message.chatId.toString()
                        this.text = "Если выпадает кубик, то поцаны тупые"
                        sender.execute(this)
                    }

                    SendDice().apply {
                        this.chatId = chatUpdate.message.chatId.toString()

                        sender.execute(this)
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }
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