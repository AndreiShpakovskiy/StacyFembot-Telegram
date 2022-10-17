package com.bot.stacy.repository.reddit

import com.bot.stacy.model.PostData
import com.bot.stacy.model.Subreddit
import com.google.gson.Gson
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse

class SingleSubLoader(
    private val subredditName: String,
    private val destination: MutableSet<PostData>
) : Runnable {

    private val httpClient = HttpClient.newBuilder().build()

    override fun run() {
        val request = HttpRequest.newBuilder()
            .uri(URI.create("https://www.reddit.com/r/$subredditName/hot.json"))
            .build()

        val response = httpClient.send(request, HttpResponse.BodyHandlers.ofString())

        if (response.statusCode() == 200) { // FIXME: Hardcode
            val posts = Gson().fromJson(response.body(), Subreddit::class.java)
                ?.data?.children?.map { it.data }
                ?: emptyList()

            destination.addAll(posts)
        }
    }
}