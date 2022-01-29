package com.github.myraBot.bunyeokga

import com.github.myraBot.diskord.common.entities.guild.Guild

suspend fun Guild.lang(string: String, arguments: suspend ArgumentBuilder.() -> Unit = {}): String {
    return this.locale.get(string, arguments)
}