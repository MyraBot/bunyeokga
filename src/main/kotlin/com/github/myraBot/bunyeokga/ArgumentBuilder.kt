package com.github.myraBot.bunyeokga

import com.github.myraBot.diskord.rest.builders.MessageBuilder

class ArgumentBuilder {
    var args = mutableMapOf<String, Any>()
    fun arg(key: String, value: Any) {
        args[key] = value
    }
}

val externalMap = mutableMapOf<MessageBuilder, suspend ArgumentBuilder.() -> Unit>()

internal var MessageBuilder.arguments: suspend ArgumentBuilder.() -> Unit
    get() = externalMap[this] ?: {}
    set(value) {
        externalMap[this] = value
    }

fun MessageBuilder.arguments(arguments: suspend ArgumentBuilder.() -> Unit) {
    this.arguments = arguments
}