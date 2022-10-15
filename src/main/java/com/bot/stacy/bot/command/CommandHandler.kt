package com.bot.stacy.bot.command

import com.bot.stacy.model.Command

/**
 * Command handler is aware of Telegram messaging model.
 *
 * I think it's okay at least for now.
 */
interface CommandHandler {
    fun handleCommand(command: Command)
}