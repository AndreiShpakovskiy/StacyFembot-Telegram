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

    private val httpClient = HttpClient.newBuilder().build()

    override fun getAllPicturePosts(subredditName: String, onPostsRetrieved: (List<PostData>?) -> Unit) {
        getSubredditContent(subredditName) {
            val picturePosts = it?.children
                ?.filter { post -> !post.data.isVideo && post.data.url != null }
                ?.map { post -> post.data }

            onPostsRetrieved(picturePosts)
        }
    }

    override fun getSubredditContent(subredditName: String, onPostsRetrieved: (SubredditContent?) -> Unit) {
        thread(start = true) { // TODO: Switch to coroutines or explore HttpClient async functionality
            val request = HttpRequest.newBuilder()
                .uri(URI.create("https://www.reddit.com/r/$subredditName/hot.json"))
                .build()

            val response = httpClient.send(request, HttpResponse.BodyHandlers.ofString())

            if (response.statusCode() == 200) { // FIXME: Hardcode
                onPostsRetrieved(Gson().fromJson(response.body(), Subreddit::class.java).data)
            } else {
                onPostsRetrieved(null)
            }
        }
    }
}