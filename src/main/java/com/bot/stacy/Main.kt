package com.bot.stacy

import com.bot.stacy.common.REDDIT_CONTENT_UPDATE_PERIOD
import com.bot.stacy.common.TopicSubs
import com.bot.stacy.repository.reddit.RedditContentLoader
import org.telegram.telegrambots.meta.TelegramBotsApi
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession

class Main {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            TelegramBotsApi(DefaultBotSession::class.java).registerBot(StacyFembot())
            RedditContentLoader(TopicSubs.all.toList()).startContentUpdate(REDDIT_CONTENT_UPDATE_PERIOD)
        }
    }
}