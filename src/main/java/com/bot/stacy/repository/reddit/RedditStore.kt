package com.bot.stacy.repository.reddit

import com.bot.stacy.model.PostData

object RedditStore : HashMap<String, MutableSet<PostData>>() {

    @Synchronized
    override fun get(key: String): MutableSet<PostData>? {
        return super.get(key)
    }

    @Synchronized
    override fun put(key: String, value: MutableSet<PostData>): MutableSet<PostData>? {
        return super.put(key, value)
    }
}