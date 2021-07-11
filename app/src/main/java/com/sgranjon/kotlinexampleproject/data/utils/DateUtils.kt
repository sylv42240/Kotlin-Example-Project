package com.sgranjon.kotlinexampleproject.data.utils

import com.sgranjon.kotlinexampleproject.data.exception.DateFormatException
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

object DateUtils {

    const val API_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss"
    const val VIEW_DATE_FORMAT = "dd/MM/yyyy"
    const val VIEW_DATE_HOUR_FORMAT = "dd-MM-yyyy HH:mm:ss"

    fun fromString(stringToFormat: String, dateFormat: String): Date {
        return SimpleDateFormat(dateFormat, Locale.getDefault()).parse(
            stringToFormat
        ) ?: throw DateFormatException()
    }

    fun fromDate(dateToFormat: Date, dateFormat: String): String {
        val newDateFormat =
            SimpleDateFormat(dateFormat, Locale.getDefault())
        return newDateFormat.format(dateToFormat)
    }
}