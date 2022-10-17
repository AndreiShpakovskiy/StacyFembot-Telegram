package com.bot.stacy.repository.joke

interface JokeRepository {
    fun getRandomJoke(): String?
}