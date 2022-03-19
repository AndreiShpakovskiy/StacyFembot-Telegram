package com.bot.stacy.repository.memes

import com.bot.stacy.common.MEME_PAGE_LINKS
import org.jsoup.Jsoup
import java.io.InputStream
import java.net.URL
import java.util.stream.Collectors
import kotlin.concurrent.thread

class DefaultMemeRepository : MemeRepository {
    private val alreadySentMemes = mutableSetOf<String>()
    private val memeCollection = mutableSetOf<String>()

    override fun getRandomMeme(pictureListener: (pictureStream: InputStream?) -> Unit) {
        thread(start = true) {
            var isCallerSatisfied = false

            pickMemelink()?.let {
                pictureListener(URL(it).openStream())
                alreadySentMemes.add(it)
                isCallerSatisfied = true
            }

            updateMemeUrls()

            if (!isCallerSatisfied) {
                val nextLink = pickMemelink()

                if (nextLink != null) {
                    pictureListener(URL(nextLink).openStream())
                    alreadySentMemes.add(nextLink)
                } else {
                    pictureListener(null)
                }
            }
        }
    }

    private fun updateMemeUrls() {
        MEME_PAGE_LINKS.forEach {
            memeCollection.addAll(getPageLinks(it))
        }
    }

    private fun getPageLinks(pageUrl: String): List<String> {
        return Jsoup.connect(pageUrl).timeout(10000).get()
            .select("img")
            .stream().filter {
                it.attr("src").contains("jpg", ignoreCase = true) &&
                        !it.attr("src").contains("style", ignoreCase = true)
            }.map { it.attr("src") }.collect(Collectors.toList())
    }

    private fun pickMemelink(): String? {
        return memeCollection.lastOrNull { !alreadySentMemes.contains(it) }
    }
}