package com.bot.stacy

import com.bot.stacy.repository.reddit.RedditContentLoader
import org.telegram.telegrambots.meta.TelegramBotsApi
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession

class Main {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            TelegramBotsApi(DefaultBotSession::class.java).registerBot(StacyFembot())
            RedditContentLoader(listOf("meme", "memes")).startContentUpdate(15000)
        }
    }
}