package com.bot.stacy.bot.command

import org.telegram.telegrambots.meta.api.methods.botapimethods.BotApiMethodMessage

interface CommandResolver {
    fun resolve(command: String): BotApiMethodMessage
}