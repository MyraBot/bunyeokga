package com.github.myraBot.bunyeokga

import com.github.myraBot.diskord.common.entities.Locale
import java.util.*

fun Locale.get(key: String): String {
    val locale = Bunyeokga.registeredLanguages[this] ?: getDefaultLanguage()
    return locale[key] as String? ?: getDefaultLanguage()[key] as String? ?: throw IllegalStateException("Cannot find $key")
}

private fun getDefaultLanguage(): Properties = Bunyeokga.registeredLanguages[Bunyeokga.defaultLang]
    ?: throw IllegalStateException("The default language file is missing")