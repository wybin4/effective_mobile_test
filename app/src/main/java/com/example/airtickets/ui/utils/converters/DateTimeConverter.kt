package com.example.airtickets.ui.utils.converters

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

interface DateTimeConverter {
    fun getTime(date: Date): String {
        val dateFormat = SimpleDateFormat("HH:mm", Locale.getDefault())
        return dateFormat.format(date)
    }

    fun parseDate(dateString: String): Date {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault())
        return try {
            dateFormat.parse(dateString) ?: throw IllegalArgumentException("Неверный формат даты: $dateString")
        } catch (e: Exception) {
            Date()
        }
    }

    fun getDMMMWithYearIfNeeded(date: Date): String {
        val calendar = Calendar.getInstance()
        val currentYear = calendar.get(Calendar.YEAR)

        calendar.time = date
        val year = calendar.get(Calendar.YEAR)

        val dateFormat = if (year == currentYear) {
            SimpleDateFormat("d MMM", Locale("ru"))
        } else {
            SimpleDateFormat("d MMM yyyy", Locale("ru"))
        }

        return dateFormat.format(date)
    }

    fun getDMMMMWithYearIfNeeded(date: Date): String {
        val calendar = Calendar.getInstance()
        val currentYear = calendar.get(Calendar.YEAR)

        calendar.time = date
        val year = calendar.get(Calendar.YEAR)

        val dateFormat = if (year == currentYear) {
            SimpleDateFormat("d MMMM", Locale("ru"))
        } else {
            SimpleDateFormat("d MMMM yyyy", Locale("ru"))
        }

        return dateFormat.format(date)
    }

    fun getDayOfWeekLowerCase(date: Date): String {
        val calendar = Calendar.getInstance()
        calendar.time = date

        return SimpleDateFormat("EE", Locale("ru"))
            .format(date)
            .lowercase(Locale.getDefault())
    }

}
