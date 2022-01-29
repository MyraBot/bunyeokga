package com.github.myraBot.bunyeokga

import com.github.myraBot.diskord.common.entities.guild.Guild
import com.github.myraBot.diskord.rest.builders.InteractionMessageBuilder
import com.github.myraBot.diskord.rest.builders.MessageBuilder


fun InteractionMessageBuilder.get(string: String): String {
    val lang = this.interaction.guildLocale.value ?: Bunyeokga.defaultLang
    return lang.get(string)
}

fun MessageBuilder.get(guild: Guild, string: String): String {
    return guild.locale.get(string)
}

