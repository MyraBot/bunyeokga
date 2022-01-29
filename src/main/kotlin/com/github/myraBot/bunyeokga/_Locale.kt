package com.github.myraBot.bunyeokga

import com.github.myraBot.diskord.common.entities.Locale
import java.util.*

suspend fun Locale.get(key: String, args: suspend ArgumentBuilder.() -> Unit = {}): String {
    val locale = Bunyeokga.registeredLanguages[this] ?: getDefaultLanguage()

    var string: String = locale[key] as String? ?: getDefaultLanguage()[key] as String? ?: throw IllegalStateException("Cannot find $key")
    val arguments = ArgumentBuilder().apply { args.invoke(this) }

    arguments.args.forEach { (k, v) -> string = string.replace("{$k}", "$v") }

    return string
}

private fun getDefaultLanguage(): Properties = Bunyeokga.registeredLanguages[Bunyeokga.defaultLang]
    ?: throw IllegalStateException("The default language file is missing")