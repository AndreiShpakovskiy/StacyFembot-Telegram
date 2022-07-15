package com.bot.stacy.repository.memes

import java.io.InputStream

interface MemeRepository {
    fun getRandomMeme(
        topic: String,
        sentMemes: Set<String> = setOf(),
        pictureListener: (pictureName: String?, pictureStream: InputStream?) -> Unit
    )
}