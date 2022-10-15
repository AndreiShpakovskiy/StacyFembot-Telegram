package com.bot.stacy.command.handler

import com.bot.stacy.ResponseMessageObserver
import com.bot.stacy.model.Command
import com.bot.stacy.repository.meme.AnyMemeRepository
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto
import org.telegram.telegrambots.meta.api.objects.InputFile
import java.net.URL

class MemeCommandHandler(private val responseMessageObserver: ResponseMessageObserver) : CommandHandler {

    private val memeRepository = AnyMemeRepository()

    override fun handleCommand(command: Command) {
        memeRepository.getRandomMeme {
            val meme = SendPhoto()
            meme.chatId = "${command.chatId}"
            meme.photo = InputFile(URL(it.mediaUrl).openStream(), it.title)
            meme.caption = it.title

            responseMessageObserver.onResponsePrepared(meme)
        }
    }
}