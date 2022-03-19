package com.bot.stacy.repository.memes

import java.io.InputStream

interface MemeRepository {
    fun getRandomMeme(pictureListener: (pictureStream: InputStream?) -> Unit)
}