package com.github.myraBot.bunyeokga

import com.github.myraBot.diskord.common.entities.guild.Guild
import com.github.myraBot.diskord.rest.builders.InteractionMessageBuilder
import com.github.myraBot.diskord.rest.builders.MessageBuilder


fun InteractionMessageBuilder.get(key: String): String {
    val lang = this.interaction.guildLocale.value ?: Bunyeokga.defaultLang
    return lang.get(key)
}

fun MessageBuilder.get(guild: Guild, key: String): String {
    return guild.locale.get(key)
}

suspend fun Guild.lang(key: String, args: suspend ArgumentBuilder.() -> Unit): String {
    return this.locale.get(key, args)
}

