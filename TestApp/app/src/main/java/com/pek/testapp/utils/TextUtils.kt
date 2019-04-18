package com.pek.testapp.utils

object TextUtils {

    fun upFirstLetter(text: String): String {
        val string = text.toLowerCase()
        if (string.isEmpty()) {
            return string
        }
        val letter = string[0]
        val upperLetter = letter.toUpperCase()
        return string.replaceFirst(letter, upperLetter)
    }

}