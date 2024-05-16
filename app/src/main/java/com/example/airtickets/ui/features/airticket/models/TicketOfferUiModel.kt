package com.example.airtickets.ui.features.airticket.models

import com.example.airtickets.R
import com.example.airtickets.ui.models.AdaptiveInt
import com.example.airtickets.ui.utils.converters.MoneyConverter

data class TicketOfferUiModel(
    override val id: Int,
    val title: String,
    val timeRange: List<String>,
    val price: Int
): AdaptiveInt, MoneyConverter {
    fun formatPrice(): String {
        return formatInt(price)
    }

    fun getImageColor(): Int {
        return when (id) {
            1 -> R.color.red
            10 -> R.color.blue
            30 -> R.color.white
            else -> throw IllegalArgumentException("Неверный id")
        }
    }
}