package com.bot.stacy

import com.bot.stacy.bot.StacyFembot
import org.telegram.telegrambots.meta.TelegramBotsApi
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession

class Main {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            TelegramBotsApi(DefaultBotSession::class.java).registerBot(StacyFembot())
        }
    }
}