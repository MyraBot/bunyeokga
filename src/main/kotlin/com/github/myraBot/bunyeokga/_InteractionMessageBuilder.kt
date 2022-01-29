package com.github.myraBot.bunyeokga

import com.github.myraBot.diskord.rest.builders.InteractionMessageBuilder


val externalMap = mutableMapOf<InteractionMessageBuilder, suspend ArgumentBuilder.() -> Unit>()

private var InteractionMessageBuilder.arguments: suspend ArgumentBuilder.() -> Unit
    get() = externalMap[this] ?: {}
    set(value) {
        externalMap[this] = value
    }

fun InteractionMessageBuilder.arguments(arguments: suspend ArgumentBuilder.() -> Unit) {
    this.arguments = arguments
}

suspend fun InteractionMessageBuilder.get(string: String): String {
    val lang = this.interaction.guildLocale.value ?: Bunyeokga.defaultLang
    return lang.get(string, this.arguments)
}

