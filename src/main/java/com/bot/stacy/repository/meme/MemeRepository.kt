package com.bot.stacy.repository.meme

import com.bot.stacy.model.Meme

interface MemeRepository {
    fun getRandomMeme(): Meme?
}