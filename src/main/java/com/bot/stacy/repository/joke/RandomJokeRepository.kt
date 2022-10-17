package com.bot.stacy.repository.joke

import com.bot.stacy.common.BotCommand
import com.bot.stacy.common.TopicSubs
import com.bot.stacy.repository.reddit.RedditContentRepository

class RandomJokeRepository : JokeRepository {
    private val redditRepository = RedditContentRepository()

    override fun getRandomJoke(): String? {
        val joke = redditRepository.getAllPicturePosts(TopicSubs[BotCommand.JOKE].toList()).shuffled().firstOrNull()

        return joke?.let {
            "${it.title}\n\n${it.selftext}"
        }
    }
}