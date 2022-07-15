package com.bot.stacy.messaging.taskHandlers

import com.bot.stacy.repository.memes.DefaultMemeRepository
import com.bot.stacy.repository.memes.MemeRepository
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto
import org.telegram.telegrambots.meta.api.objects.InputFile
import org.telegram.telegrambots.meta.api.objects.Update
import org.telegram.telegrambots.meta.bots.AbsSender

class DefaultMemeSender(
    private val memeRepository: MemeRepository = DefaultMemeRepository()
) : MemeSender {
    private val sentMemes: MutableMap<String, MutableSet<String>> = mutableMapOf()

    override fun send(topic: String, sender: AbsSender, chatUpdate: Update) {
        val chatId = chatUpdate.message.chatId.toString()

        SendMessage().apply {
            this.chatId = chatId
            this.text = "Ищу свежий мем"
            sender.execute(this)
        }

        if (sentMemes[chatId] == null) {
            sentMemes[chatId] = mutableSetOf()
        }

        memeRepository.getRandomMeme(topic, sentMemes[chatId] ?: mutableSetOf()) { pictureName, pictureStream ->
            if (pictureStream != null) {
                SendPhoto().apply {
                    this.chatId = chatId
                    this.photo = InputFile(pictureStream, System.currentTimeMillis().toString())
                    sender.execute(this)
                }

                sentMemes[chatId]?.add(pictureName ?: "")
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