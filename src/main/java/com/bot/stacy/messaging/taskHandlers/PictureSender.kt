package com.bot.stacy.messaging.taskHandlers

import com.bot.stacy.repository.memes.DefaultMemeRepository
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto
import org.telegram.telegrambots.meta.api.objects.InputFile
import org.telegram.telegrambots.meta.api.objects.Update
import org.telegram.telegrambots.meta.bots.AbsSender

class PictureSender : MessageSender {
    private val pictureRepository = DefaultMemeRepository()

    override fun sendMessage(sender: AbsSender, chatUpdate: Update) {
        SendMessage().apply {
            this.chatId = chatUpdate.message.chatId.toString()
            this.text = "Ищу свежий мем"
            sender.execute(this)
        }

        pictureRepository.getRandomMeme {
            if (it != null) {
                SendPhoto().apply {
                    this.chatId = chatUpdate.message.chatId.toString()
                    this.photo = InputFile(it, System.currentTimeMillis().toString())
                    sender.execute(this)
                }
            } else {
                SendMessage().apply {
                    this.chatId = chatUpdate.message.chatId.toString()
                    this.text = "Я не смогла найти ничего нового. Подожди несколько минут и попробуй еще раз 😞"
                    sender.execute(this)
                }
            }
        }
    }
}