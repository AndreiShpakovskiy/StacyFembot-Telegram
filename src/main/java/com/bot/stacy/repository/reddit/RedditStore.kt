package com.bot.stacy.repository.reddit

import com.bot.stacy.model.PostData

object RedditStore : HashMap<String, MutableList<PostData>>() {

    @Synchronized
    override fun get(key: String): MutableList<PostData>? {
        return super.get(key)
    }

    @Synchronized
    override fun put(key: String, value: MutableList<PostData>): MutableList<PostData>? {
        return super.put(key, value)
    }
}