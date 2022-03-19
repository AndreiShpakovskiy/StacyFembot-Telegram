package com.bot.stacy.repository.config

import java.io.IOException
import java.util.*

class DefaultConfigRepository : ConfigRepository {
    companion object {
        private const val BOT_PROPERTIES_FILE_NAME = "bot.properties"
        private const val BOT_TOKEN_PROPERTY_NAME = "bot.token"
        private const val BOT_USERNAME_PROPERTY_NAME = "bot.username"
    }

    override val botToken: String?
        get() = getBotProperty(BOT_TOKEN_PROPERTY_NAME)

    override val botUsername: String?
        get() = getBotProperty(BOT_USERNAME_PROPERTY_NAME)

    private fun getBotProperty(propertyName: String): String? {
        try {
            DefaultConfigRepository::class.java.classLoader
                .getResourceAsStream(BOT_PROPERTIES_FILE_NAME).use { inputStream ->
                    val properties = Properties()
                    properties.load(inputStream)
                    return properties.getProperty(propertyName)
                }
        } catch (ex: IOException) {
            ex.printStackTrace()
        }

        return null;
    }
}