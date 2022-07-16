package com.bot.stacy.repository.memes

import com.bot.stacy.common.MemeSubreddits
import org.jsoup.Jsoup
import java.io.InputStream
import java.net.URL
import java.util.stream.Collectors
import kotlin.concurrent.thread

class DefaultMemeRepository : MemeRepository {
    private val memeCollection: MutableMap<String, MutableSet<String>> = mutableMapOf()

    override fun getRandomMeme(
        topic: String,
        sentMemes: Set<String>,
        pictureListener: (pictureName: String?, pictureStream: InputStream?) -> Unit
    ) {
        println("Get meme for topic: $topic")
        thread(start = true) {
            var isCallerSatisfied = false

            pickMemelink(topic, sentMemes)?.let {
                pictureListener(it, URL(it).openStream())
                isCallerSatisfied = true
            }

            updateMemeUrls(topic)

            if (!isCallerSatisfied) {
                val nextLink = pickMemelink(topic, sentMemes)

                if (nextLink != null) {
                    pictureListener(nextLink, URL(nextLink).openStream())
                } else {
                    pictureListener(null, null)
                }
            }
        }
    }

    private fun updateMemeUrls(memeTopic: String) {
        MemeSubreddits[memeTopic]?.forEach {
            val pictureLinks = try {
                getPictureLinks(it)
            } catch (e: Exception) {
                emptyList()
            }

            println("Memes [$memeTopic] -> $pictureLinks")

            if (memeCollection[memeTopic] == null) {
                memeCollection[memeTopic] = mutableSetOf()
            }

            memeCollection[memeTopic]?.addAll(pictureLinks)
        }
    }

    private fun getPictureLinks(pageUrl: String): List<String> {
        return Jsoup.connect(pageUrl).timeout(10000).get()
            .select("img")
            .stream().filter {
                it.attr("src").contains("jpg", ignoreCase = true) &&
                        !it.attr("src").contains("style", ignoreCase = true)
            }.map { it.attr("src") }
            .filter { !it.contains("external-preview") }
            .collect(Collectors.toList())
    }

    private fun pickMemelink(memeTopic: String, sentMemes: Set<String>): String? {
        println(memeCollection[memeTopic])
        return memeCollection[memeTopic]?.lastOrNull { !sentMemes.contains(it) }
    }
}