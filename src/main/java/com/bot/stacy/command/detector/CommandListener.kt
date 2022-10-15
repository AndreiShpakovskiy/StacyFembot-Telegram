package com.bot.stacy.command.detector

import com.bot.stacy.model.Command

interface CommandListener {
    fun onCommand(command: Command)
}