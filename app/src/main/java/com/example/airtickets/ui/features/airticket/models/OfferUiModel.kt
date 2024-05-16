package com.example.airtickets.ui.features.airticket.models

import com.example.airtickets.R
import com.example.airtickets.ui.models.AdaptiveInt
import com.example.airtickets.ui.utils.converters.MoneyConverter

data class OfferUiModel(
    override val id: Int,
    val title: String,
    val town: String,
    val price: Int
): AdaptiveInt, MoneyConverter {
    fun formatPrice(): String {
        return "от ${formatInt(price)}"
    }

    fun getImage(): Int {
        return when (id) {
            1 -> R.drawable.air_tickets_pic_1
            2 -> R.drawable.air_tickets_pic_2
            3 -> R.drawable.air_tickets_pic_3
            else -> throw IllegalArgumentException("Неверный id")
        }
    }
}