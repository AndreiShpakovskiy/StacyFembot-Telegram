package com.bot.stacy.command.detector

import org.telegram.telegrambots.meta.api.objects.Message

abstract class CommandDetector(
    protected val commandListener: CommandListener
) {
    abstract fun processMessage(message: Message)
}