package com.bot.stacy.command.handler

import com.bot.stacy.ResponseMessageObserver
import com.bot.stacy.model.Command
import com.bot.stacy.repository.joke.RandomJokeRepository
import org.telegram.telegrambots.meta.api.methods.send.SendMessage

class JokeCommandHandler(responseMessageObserver: ResponseMessageObserver) :
    LongResponseCommandHandler(responseMessageObserver) {
    private val jokeRepository = RandomJokeRepository()

    override val initialMessageText: String = "Looking for a new joke!"

    override fun handleCommand(command: Command) {
        sendInitialMessage("${command.chatId}")

        val joke = jokeRepository.getRandomJoke()

        if (joke != null) {
            val memeMessage = SendMessage()
            memeMessage.chatId = "${command.chatId}"
            memeMessage.text = joke

            responseMessageObserver.onResponsePrepared(memeMessage)
        } else {
            // TODO: Make it a part of base class functionality
            val responseMessage = SendMessage()
            responseMessage.chatId = "${command.chatId}"
            responseMessage.text = "Couldn't find a joke for you, try again"

            responseMessageObserver.onResponsePrepared(responseMessage)
        }
    }
}