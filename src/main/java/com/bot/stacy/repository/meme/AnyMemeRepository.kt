package com.bot.stacy.repository.meme

import com.bot.stacy.common.BotCommand
import com.bot.stacy.common.TopicSubs
import com.bot.stacy.model.Meme
import com.bot.stacy.repository.reddit.RedditContentRepository

class AnyMemeRepository : MemeRepository {

    private val redditRepository = RedditContentRepository()

    override fun getRandomMeme(): Meme? {
        val memePost = redditRepository.getAllPicturePosts(TopicSubs[BotCommand.MEME].toList()).shuffled().firstOrNull()

        return memePost?.let {
            Meme(title = memePost.title, mediaUrl = memePost.url!!) // Post URL is guaranteed to be non-null here
        }
    }
}