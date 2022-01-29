package com.github.myraBot.bunyeokga

import com.github.myraBot.diskord.rest.MessageTransformer
import com.github.myraBot.diskord.rest.builders.MessageBuilder

abstract class LanguageTransformer : MessageTransformer {

    override suspend fun onText(builder: MessageBuilder, text: String): String {
        var string = text
        val arguments = ArgumentBuilder().apply { builder.arguments.invoke(this) }
        arguments.args.forEach { string = string.replace("{$it.key}", "${it.value}") }
        return string
    }

}