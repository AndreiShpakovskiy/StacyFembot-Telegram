package com.bot.stacy.common

object MemeSubreddits : HashMap<String, List<String>>() {
    private val MIXED = listOf(
        "https://www.reddit.com/r/HolUp/",
        "https://www.reddit.com/r/holdup/",
        "https://www.reddit.com/r/youdontsurf/",
        "https://www.reddit.com/r/SuddenlyGay/",
        "https://www.reddit.com/r/theyknew/",
        "https://www.reddit.com/r/2Russophobic4you/",
        "https://www.reddit.com/r/lgbtmemes/",
        "https://www.reddit.com/r/ANormalDayInRussia/",
        "https://www.reddit.com/r/Angryupvote/",
        "https://www.reddit.com/r/technicallythetruth/",
        "https://www.reddit.com/r/Chicken_Thoughts/",
        "https://www.reddit.com/r/Offensivejokes/",
        "https://www.reddit.com/r/Feminism/",
        "https://www.reddit.com/r/wholesomememes/",
        "https://www.reddit.com/r/lgbtmemes/"
    )

    private val ANIME = listOf(
        "https://www.reddit.com/r/animememes/",
        "https://www.reddit.com/r/Animemes/",
        "https://www.reddit.com/r/anime_irl/",
        "https://www.reddit.com/r/attackontitanmemes/",
        "https://www.reddit.com/r/Hentai69Memes/",
        "https://www.reddit.com/r/narutomemes/",
        "https://www.reddit.com/r/TheCloneAnimes/",
        "https://www.reddit.com/r/AnimeFunny/",
        "https://www.reddit.com/r/animecirclejerk/",
        "https://www.reddit.com/r/goodanimemes/",
        "https://www.reddit.com/r/animeshitmemes/",
        "https://www.reddit.com/r/MangaMemes/"
    )

    override fun get(key: String): List<String>? {
        return when (key) {
            BotCommand.Meme.ANY -> MIXED
            BotCommand.Meme.ANIME -> ANIME
            else -> super.get(key)
        }
    }
}