package com.bot.stacy.common

object TopicSubs : HashMap<String, Set<String>>() {

    private val MEME_SUBS = setOf(
        "HolUp",
        "holdup",
        "youdontsurf",
        "SuddenlyGay",
        "theyknew",
        "2Russophobic4you",
        "lgbtmemes",
        "ANormalDayInRussia",
        "Angryupvote",
        "technicallythetruth",
        "Chicken_Thoughts",
        "Offensivejokes",
        "Feminism",
        "wholesomememes",
        "lgbtmemes"
    )

    private val ANIME_MEME_SUBS = setOf(
        "animememes",
        "Animemes",
        "anime_irl",
        "attackontitanmemes",
        "Hentai69Memes",
        "narutomemes",
        "TheCloneAnimes",
        "AnimeFunny",
        "animecirclejerk",
        "goodanimemes",
        "animeshitmemes",
        "MangaMemes"
    )

    private val JOKE_SUBS = setOf(
        "Jokes"
    )

    init {
        this[BotCommand.MEME] = MEME_SUBS
        this[BotCommand.ANIMEME] = ANIME_MEME_SUBS
        this[BotCommand.JOKE] = JOKE_SUBS
    }

    override fun get(key: String): Set<String> {
        return super.get(key) ?: emptySet()
    }

    val all get(): Set<String> = this.flatMap { it.value }.toSet()
}