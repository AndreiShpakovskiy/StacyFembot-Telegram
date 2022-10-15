package com.bot.stacy

import org.telegram.telegrambots.meta.api.methods.BotApiMethod
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto
import java.io.Serializable

interface ResponseMessageObserver {
    fun <T : Serializable?, Method : BotApiMethod<T>?> onResponsePrepared(message: Method)
    fun onResponsePrepared(message: SendPhoto)
}