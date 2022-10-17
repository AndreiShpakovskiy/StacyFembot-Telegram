package com.bot.stacy.repository.reddit

import java.util.Timer
import java.util.TimerTask
import java.util.concurrent.Executors

// FIXME: I don't really like scheduling logic, but let's keep it for now
class RedditContentLoader(private val trackedSubsList: List<String>) {

    private val executorService = Executors.newScheduledThreadPool(5)

    private fun updateContent() {
        trackedSubsList.forEach {
            if (RedditStore[it] == null) {
                RedditStore[it] = mutableListOf()
            }

            executorService.execute(SingleSubLoader(subredditName = it, destination = RedditStore[it]!!))
        }
    }

    fun startContentUpdate(updatePeriodMillis: Long) {
        Timer().scheduleAtFixedRate(object : TimerTask() {
            override fun run() {
                updateContent()
            }
        }, 0, updatePeriodMillis)
    }
}