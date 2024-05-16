package com.example.airtickets.ui.utils.converters

interface MoneyConverter {
    fun formatInt(value: Int): String {
        val formattedValue = value.toString().replace(Regex("(\\d)(?=(\\d{3})+(?!\\d))"), "$1 ")
        return "$formattedValue ₽"
    }
}
