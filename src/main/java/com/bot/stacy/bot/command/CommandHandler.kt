package com.bot.stacy.bot.command

import com.bot.stacy.model.Command

interface CommandHandler {
    fun handleCommand(command: Command)
}