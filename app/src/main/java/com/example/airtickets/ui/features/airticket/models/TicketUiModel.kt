package com.example.airtickets.ui.features.airticket.models

import com.example.airtickets.ui.models.AdaptiveInt
import com.example.airtickets.ui.utils.converters.DateTimeConverter
import com.example.airtickets.ui.utils.converters.MoneyConverter
import com.example.data.features.airticket.models.HandLuggage
import com.example.data.features.airticket.models.Luggage
import java.util.Date
import java.util.Locale

data class TicketUiModel(
    override val id: Int,
    val badge: String?,
    val price: Int,
    val arrivalDate: Date,
    val departureDate: Date,
    val arrivalAirport: String,
    val departureAirport: String,
    val hasTransfer: Boolean
): AdaptiveInt, MoneyConverter, DateTimeConverter {
    fun formatPrice(): String {
        return formatInt(price)
    }

    fun hasBadge(): Boolean {
        return badge != null
    }

    fun departureTime(): String {
        return getTime(departureDate)
    }

    fun arrivalTime(): String {
        return getTime(arrivalDate)
    }

    fun flightDuration(): String {
        val departureTime = departureDate.time
        val arrivalTime = arrivalDate.time
        val durationMillis = arrivalTime - departureTime
        val totalHours = durationMillis / (1000 * 60 * 60).toFloat()
        val hours = totalHours.toInt()
        val minutes = ((totalHours - hours) * 60).toInt()

        return String.format(Locale.getDefault(), "%d.%02dч", hours, minutes) + " в пути"
    }

}