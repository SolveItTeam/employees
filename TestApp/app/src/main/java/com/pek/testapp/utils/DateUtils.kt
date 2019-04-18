package com.pek.testapp.utils

import timber.log.Timber
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

object DateUtils {

    fun dateToFormattedString(date: Date) =
        SimpleDateFormat("dd.MM.yyyy", Locale.getDefault()).format(date)

    fun getAge(date: Date): Int {
        val calendar = Calendar.getInstance()
        val currentMillis = calendar.timeInMillis
        val currentYear = calendar.get(Calendar.YEAR)
        calendar.time = date
        val year = calendar.get(Calendar.YEAR)

        var age = currentYear - year
        if (currentMillis > calendar.timeInMillis) {
            age++
        }

        return age
    }

    fun parseDate(patterns: List<String>, value: String): Date? {
        for (pattern in patterns) {
            try {
                return stringToDate(pattern, value)
            } catch (e: ParseException) {
                Timber.e(e)
            }
        }

        return null
    }

    private fun stringToDate(pattern: String, value: String): Date = SimpleDateFormat(pattern, Locale.getDefault())
        .apply { isLenient = false }
        .parse(value)

}