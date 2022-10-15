package com.bot.stacy

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