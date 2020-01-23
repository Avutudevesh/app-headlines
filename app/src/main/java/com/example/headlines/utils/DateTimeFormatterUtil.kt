package com.example.headlines.utils

import java.text.SimpleDateFormat
import java.util.*

class DateTimeFormatterUtil {
    companion object {
        private val inputDateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.ENGLISH)
        private val outputDateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)

        fun formatDateTime(dateTime: String): String {
            val inputDate = try {
                inputDateFormat.parse(dateTime)
            } catch (e: Exception) {
                " "
            }
            return if (inputDate != " ") {
                outputDateFormat.format(inputDate)
            } else {
                " "
            }
        }
    }
}