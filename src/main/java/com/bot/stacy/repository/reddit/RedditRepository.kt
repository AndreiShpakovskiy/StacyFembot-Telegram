package com.bot.stacy.repository.reddit

import com.bot.stacy.model.PostData
import com.bot.stacy.model.SubredditContent

interface RedditRepository {
    fun getSubredditContent(subredditName: String, onPostsRetrieved: (SubredditContent?) -> Unit)
    fun getAllPicturePosts(subredditName: String, onPostsRetrieved: (List<PostData>?) -> Unit)
}