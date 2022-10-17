package com.bot.stacy.repository.meme

import com.bot.stacy.model.Meme
import com.bot.stacy.repository.reddit.RedditContentRepository
import com.bot.stacy.repository.reddit.RedditStore

class AnyMemeRepository : MemeRepository {

    private val redditRepository = RedditContentRepository()

    override fun getRandomMeme(): Meme? {
        val memePost = redditRepository.getAllPicturePosts(RedditStore.keys.toList()).shuffled().firstOrNull()

        return memePost?.let {
            Meme(title = memePost.title, mediaUrl = memePost.url!!) // Post URL is guaranteed to be non-null here
        }
    }
}