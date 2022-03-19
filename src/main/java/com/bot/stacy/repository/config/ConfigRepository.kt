package com.bot.stacy.repository.config

interface ConfigRepository {
    val botToken: String?
    val botUsername: String?
}