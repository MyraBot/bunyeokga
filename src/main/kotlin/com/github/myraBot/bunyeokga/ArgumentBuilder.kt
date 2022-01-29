package com.github.myraBot.bunyeokga

class ArgumentBuilder {
    var args = mutableMapOf<String, Any>()
    fun arg(key: String, value: Any) {
        args[key] = value
    }
}