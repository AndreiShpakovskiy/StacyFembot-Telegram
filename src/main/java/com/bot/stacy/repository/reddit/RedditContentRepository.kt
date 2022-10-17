package com.bot.stacy.repository.reddit

import com.bot.stacy.model.PostData
import com.bot.stacy.model.Subreddit
import com.bot.stacy.model.SubredditContent
import com.google.gson.Gson
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse
import kotlin.concurrent.thread

class RedditContentRepository : RedditRepository {

    override fun getAllPicturePosts(subredditNames: List<String>): List<PostData> {
        return subredditNames.flatMap { getAllPicturePosts(it) ?: emptyList() }
    }

    override fun getAllPicturePosts(subredditName: String): List<PostData>? {
        return RedditStore[subredditName]?.filter { post -> !post.isVideo && post.url != null }
    }
}