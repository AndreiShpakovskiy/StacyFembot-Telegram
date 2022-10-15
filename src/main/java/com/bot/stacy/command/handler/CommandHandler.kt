package com.bot.stacy.command.handler

import com.bot.stacy.model.Command

/**
 * Command handler is aware of Telegram messaging model.
 *
 * I think it's okay at least for now.
 */
interface CommandHandler {
    fun handleCommand(command: Command)
}