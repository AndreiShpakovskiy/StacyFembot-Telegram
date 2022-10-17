package com.bot.stacy.command.handler

import com.bot.stacy.ResponseMessageObserver
import com.bot.stacy.model.Command
import com.bot.stacy.repository.meme.AnyMemeRepository
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto
import org.telegram.telegrambots.meta.api.objects.InputFile
import java.net.URL

class MemeCommandHandler(responseMessageObserver: ResponseMessageObserver) :
    LongResponseCommandHandler(responseMessageObserver) {

    private val memeRepository = AnyMemeRepository()

    override val initialMessageText: String = "Looking for a new meme!"

    override fun handleCommand(command: Command) {
        sendInitialMessage("${command.chatId}")

        val meme = memeRepository.getRandomMeme()
        if (meme != null) {
            val memeMessage = SendPhoto()
            memeMessage.chatId = "${command.chatId}"
            memeMessage.photo = InputFile(URL(meme.mediaUrl).openStream(), meme.title)
            memeMessage.caption = meme.title

            responseMessageObserver.onResponsePrepared(memeMessage)
        } else {
            // TODO: Make it a part of base class functionality
            val responseMessage = SendMessage()
            responseMessage.chatId = "${command.chatId}"
            responseMessage.text = "Couldn't find a meme for you, try again"

            responseMessageObserver.onResponsePrepared(responseMessage)
        }
    }
}