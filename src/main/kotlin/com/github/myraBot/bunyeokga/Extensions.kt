package com.github.myraBot.bunyeokga

import com.github.myraBot.diskord.common.entities.Locale
import com.github.myraBot.diskord.common.entities.guild.Guild
import com.github.myraBot.diskord.rest.builders.InteractionMessageBuilder
import com.github.myraBot.diskord.rest.builders.MessageBuilder
import java.util.*

suspend fun Locale.get(key: String, args: suspend ArgumentBuilder.() -> Unit = {}): String {
    val locale = Bunyeokga.registeredLanguages[this] ?: getDefaultLanguage()
    var string = locale[key] as String? ?: getDefaultLanguage()[key] as String? ?: throw IllegalStateException("Cannot find $key")
    val arguments = ArgumentBuilder().apply { args.invoke(this) }
    arguments.args.forEach {
        string = string.replace("{${it.key}}", it.value.toString())
    }
    return string
}

suspend fun Guild.lang(key: String, args: suspend ArgumentBuilder.() -> Unit = {}): String = this.locale.get(key, args)

suspend fun InteractionMessageBuilder.get(key: String): String {
    val lang = this.interaction.guildLocale.value ?: Bunyeokga.defaultLang
    return lang.get(key, this.arguments)
}

suspend fun MessageBuilder.get(guild: Guild, key: String): String = guild.locale.get(key, this.arguments)

private fun getDefaultLanguage(): Properties = Bunyeokga.registeredLanguages[Bunyeokga.defaultLang]
    ?: throw IllegalStateException("The default language file is missing")