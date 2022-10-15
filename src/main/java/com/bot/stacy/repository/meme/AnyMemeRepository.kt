package com.bot.stacy.repository.meme

import com.bot.stacy.model.Meme
import com.bot.stacy.repository.reddit.RedditContentRepository

class AnyMemeRepository : MemeRepository {

    private val redditRepository = RedditContentRepository()

    override fun getRandomMeme(onMeme: (Meme) -> Unit) {
        redditRepository.getAllPicturePosts("meme") {
            val post = it?.shuffled()?.first()
            post?.let {
                onMeme(Meme(title = post.title, mediaUrl = post.url!!)) // Post URL is guaranteed to be non-null here
            }
        }
    }
}