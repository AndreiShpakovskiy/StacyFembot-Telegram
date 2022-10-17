package com.bot.stacy.repository.reddit

import com.bot.stacy.model.PostData

interface RedditRepository {
    fun getAllPicturePosts(subredditName: String): List<PostData>?
    fun getAllPicturePosts(subredditNames: List<String>): List<PostData>?
}