package com.bot.stacy.model

data class Subreddit(
    val data: SubredditContent
)

data class SubredditContent(
    val children: List<Post>
)

data class Post(
    val data: PostData
)

data class PostData(
    val title: String?,
    val url: String?,
    val selftext: String?,
    val isVideo: Boolean
)