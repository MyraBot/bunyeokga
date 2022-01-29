package com.github.myraBot.bunyeokga

import com.github.myraBot.diskord.common.Diskord
import com.github.myraBot.diskord.common.entities.Locale
import com.github.myraBot.diskord.rest.builders.InteractionMessageBuilder
import com.github.myraBot.diskord.rest.builders.MessageBuilder
import java.util.*

object Bunyeokga {

    var directory: String = ""
    lateinit var defaultLang: Locale
    val registeredLanguages: MutableMap<Locale, Properties> = mutableMapOf()

    fun getLanguageByIso(isoCode: String): Locale? = Locale.values().find { it.code == isoCode }

    fun loadLanguages() {
        Locale.values().forEach {
            val inputStream = Bunyeokga.javaClass.classLoader.getResourceAsStream("$directory${it.code}.properties") ?: return@forEach
            val file = Properties()
            file.load(inputStream)
            registeredLanguages[it] = file
        }
    }

}

fun bunyeokga(bunyeokga: Bunyeokga.() -> Unit) = Bunyeokga.apply(bunyeokga)

